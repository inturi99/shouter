(ns shouter.models.shout
  (:require [clojure.java.jdbc :as sql]))

;; (def spec (or (System/getenv "DATABASE_URL")
;;              "postgresql://localhost:5432/shouter"))

(let [db-host "localhost"
      db-port 5432
      db-name "shouter"]
  (def spec {:classname "org.postgresql.Driver" ; must be in classpath
           :subprotocol "postgresql"
           :subname (str "//" db-host ":" db-port "/" db-name)
                                        ; Any additional keys are passed to the driver
                                        ; as driver-specific properties.
           :user "postgres"
             :password "Design_20"}))

(defn all []
  (into [] (sql/query spec ["select * from shouts order by id desc"])))

(defn create [shout]
  (sql/insert! spec :shouts [:body] [shout]))

