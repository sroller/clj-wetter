;; vim:syntax=clojure:

(set-env! :dependencies '[[clj-http "3.8.0"]
                          [org.clojure/clojure "1.9.0"]
                          [org.clojure/data.json "0.2.6"]])

(require '[clj-http.client :as client])
(require '[clojure.data.json :as json])

(deftask fire
 "Announces that something is on fire"
 [t thing     THING str  "The thing that's on fire"
  p pluralize       bool "Whether to pluralize"]
 (let [verb (if pluralize "are" "is")]
   (println "My" thing verb "on fire!")))

(deftask wetter
"Wetter daten"
[]
 (println "hole Wetterdaten")
 (let [request 
       (str "http://api.openweathermap.org/data/2.5/weather?q=Waterloo,ca&units=metric&" "appid=" (System/getenv "OPENWEATHERMAP_APPID"))]
 (println (str "request:" request))
 (clojure.pprint/pprint (json/read-str (:body (client/get request))))))
