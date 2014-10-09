# clj-hacker-news

A Clojure library designed to interact with the Hacker News API (https://github.com/HackerNews/API).

## Usage

Require `clj-hacker-news` in your namespace declaration.
```
(:require [clj-hacker-news :as hn])
```

Get IDs of top `n` stories.
```
=> (hn/get-top-n 5)
(8430412 8428632 8430544 8430096 8428522)
```

Get data about a particular story.
```
=> (hn/retrieve-item 8428632)
{:text "", :score 616, :title "The Horror of a 'Secure Golden Key'", :by "jashkenas", :kids [8429141 8428796 8429540 8428880 8428883 8428779 8429498 8428795 8430030 8429167 8430805 8429023 8429642 8429612 8429310 8430366 8428770], :url "https://keybase.io/blog/2014-10-08/the-horror-of-a-secure-golden-key", :type "story", :time 1412793466, :id 8428632}
```

Get a user's profile.
```
=> (retrieve-user "dopamean")
{:about "", :created 1345353440, :delay 0, :id "dopamean", :karma 1124, :submitted [8418070 8380814 8380131 8378202 8370760 8313647 8232918 8197760 8192684 8148563 8148562 8145630 8087869 8086022 7935687 7893898 7823639 7812053 7771136 7771135 7771131 7730696 7722263 7713713 7704093 7702584 ...]}
```

Grab the top 5 posts and pretty print a preview of each post.
```
=> (def top-5-stories (map hn/retrieve-item (hn/get-top-n 5)))
#'clj-hacker-news.core/top-5-stories
=> (doseq [x (map hn/story-preview top-5-stories)] (println x))
Title: Google Now vs. Siri vs. Cortana – The Great Knowledge Box Showdown
Link:  https://www.stonetemple.com/great-knowledge-box-showdown/
Date:  1412819228
Score: 95
By:    nreece

Title: The Horror of a 'Secure Golden Key'
Link:  https://keybase.io/blog/2014-10-08/the-horror-of-a-secure-golden-key
Date:  1412793466
Score: 615
By:    jashkenas

Title: What kids around the world eat for breakfast
Link:  http://www.nytimes.com/interactive/2014/10/08/magazine/eaters-all-over.html?action=click&pgtype=Homepage&version=Moth-Visible&module=inside-nyt-region&region=inside-nyt-region&WT.nav=inside-nyt-region
Date:  1412822506
Score: 34
By:    mhb

Title: Can Google's search engine find profits? (1999)
Link:  http://www.zdnet.com/news/can-googles-search-engine-find-profits/102541
Date:  1412813027
Score: 49
By:    grinich

Title: Move Fast and Break Nothing
Link:  http://zachholman.com/talk/move-fast-break-nothing/
Date:  1412792571
Score: 295
By:    bpierre

=>
```

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
