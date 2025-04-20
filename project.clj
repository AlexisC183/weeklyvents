(defproject weeklyvents "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[com.github.alexisc183/forconsol "1.0.0"]
                 [com.github.alexisc183/forctional "1.0.0"]
                 [org.clojure/clojure "1.10.0"]]
  :java-source-paths ["src/weeklyvents"]
  :main ^:skip-aot weeklyvents.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
