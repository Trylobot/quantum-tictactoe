(defproject quantum-tictactoe "0.0.1-SNAPSHOT"
  :dependencies [
    [org.clojure/clojure "1.9.0"]
    [org.clojure/clojurescript "1.10.339"]
    [reagent "0.8.1"]
    [re-frame "0.10.5"]
    [figwheel "0.5.16"]
    [re-com "2.1.0"]
  ]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.16" :exclusions [cider/cider-nrepl]]  ]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {
    :builds [{
      :id "dev"
      :source-paths ["src/cljs"]
      :figwheel {
        :on-jsload "quantum-tictactoe.core/mount-root" }
      :compiler {
        :main quantum-tictactoe.core
        :output-to "resources/public/js/compiled/app.js"
        :output-dir "resources/public/js/compiled/out"
        :asset-path "js/compiled/out"
        :source-map-timestamp true }
    } {
      :id "min"
      :source-paths ["src/cljs"]
      :compiler {
        :main quantum-tictactoe.core
        :output-to "resources/public/js/compiled/app.js"
        :optimizations :advanced
        :pretty-print false }
    }]
  }
  :aliases {
    "fw" ["do" "clean," "figwheel" "dev"]
    "release" ["do" "clean," "cljsbuild" "once" "min"]
  }
)
