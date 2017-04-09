(defproject bookscraper "0.1.0-SNAPSHOT"
  :description "take web page with a list of pdf files, grab them all, combine them."
  :url "https://github.com/paultopia/scrape-ebook"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [pdfboxing "0.1.9"]
                 [enlive "1.1.6"]]
  :main ^:skip-aot bookscraper.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
