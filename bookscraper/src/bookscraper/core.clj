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

(def filelist (atom []))

(defn save-temp-file [link]
  (let [tf (java.io.File/createTempFile link ".pdf")]
    (swap! filelist conj (.getAbsolutePath tf))
    (with-open [in (io/input-stream link)
                out (io/output-stream tf)]
      (io/copy in out))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [links (get-pdf-links "http://www.oreilly.com/openbook/make3/book/index.html")]
    (save-temp-file (first links))
    (println @filelist)))
