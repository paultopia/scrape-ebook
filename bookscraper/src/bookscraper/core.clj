(ns bookscraper.core
  (:gen-class)
   (:require 
    [clojure.string :refer [ends-with? starts-with? last-index-of]]
    [pdfboxing.merge :refer [merge-pdfs]]
    [clojure.java.io :as io]
    [net.cgrand.enlive-html :as html]))


(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

(defn get-links [url]
  (map #(str (:href (:attrs %))) (html/select (fetch-url url) [:a])))

(defn appender [url link]
  (str (subs url 0
             (+ 1 (last-index-of url "/")))
       link))

(defn fix-link [url link]
  (if (starts-with? link "http")
    link
    (appender url link)))

(defn get-pdf-links [url]
  (map (partial fix-link url) (filter #(ends-with? % ".pdf") (get-links url))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
