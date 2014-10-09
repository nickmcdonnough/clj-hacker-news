(ns clj-hacker-news.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [clj-time.core :as t]))

;; Live API urls.
(def top-100 "https://hacker-news.firebaseio.com/v0/topstories/.json")
(def max-item "https://hacker-news.firebaseio.com/v0/maxitem.json")
(def recent-updates "https://hacker-news.firebaseio.com/v0/updates.json")

;; Items API url.
(def items-api-base "https://hacker-news.firebaseio.com/v0/item/")
;; Users API url.
(def users-api-base "https://hacker-news.firebaseio.com/v0/user/")


(defn retrieve-item
  "Retrieve an item (post, comment, etc.)"
  [item-number]
  (let [response (client/get (str items-api-base item-number ".json"))]
    (when (client/success? response) (->> response :body json/read-json))))

(defn retrieve-user
  "Retrieve a user"
  [user]
  (let [response (client/get (str users-api-base user ".json"))]
    (when (client/success? response) (->> response :body json/read-json))))

(defn get-top-n
  "From the current top 100 stories grab the first n."
  [n]
  (->> (client/get top-100)
       :body
       json/read-json
       (take n)))

(defn story-preview
  "Pretty print story"
  [story]
  (let [{:keys [title url time score by]} story]
    (str
      "Title: " title "\n"
      "Link:  " url "\n"
      "Date:  " time "\n"
      "Score: " score "\n"
      "By:    " by "\n")))

(defn comment-preview
  "Pretty print story comments"
  [comment]
  (let [{:keys [text score time by]} comment]
    (str
      "Text:  " text "\n"
      "Date:  " time
      "Score: " score "\n"
      "By:    " by "\n")))

(defn preview-item
  "Preview dispatching method. Takes stories, comments, polls, and poll options
  and dispatches them to their respective pretty printing function."
  [item-id]
  (let [item (retrieve-item)]
    (case (:type item)
      "story" (story-preview item)
      "comment" (comment-preview item)
      "poll" (poll-preview item)
      "pollopt" (pollopt-preview item))))

(defn preview-user
  "Takes a map of user information received as JSON from the Users API and
  returns a prettied up string ready to be printed."
  [user]
  (let [{:keys [about created delay id karma]} user]
    (str
      "User:    " id "\n"
      "Karma:   " karma "\n"
      "Created: " created "\n"
      "Delay:   " delay "\n"
      "About:   " about "\n")))
