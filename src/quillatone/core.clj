(ns quillatone.core
  (:require
   [quil.core :as q]
   [overtone.live :refer :all]
    [overtone.inst.drum :as drum]
    [overtone.inst.synth :as s]
    [overtone.inst.sampled-piano :as p]
    ))


(def num 10)

(defn draw-circles []
  (dorun
   (for [i (range 0 num)]
     (let [x      (q/random (q/width))
           y      (q/random (q/height))
           radius (+ (q/random 100) 10)]
       (q/no-stroke)
       (q/ellipse x y (* 2 radius) (* 2 radius))
       (q/stroke 0 150)
       (q/ellipse x y 10 10)))))

(defn mouse-moved []
  (let [x (q/mouse-x) y (q/mouse-y)]
    (reset! (q/state :mouse-position) [x y])))

(defn capture-click []
  (let [[x y]  @(q/state :mouse-position)]
    (p/sampled-piano) 
    (draw-circles)))

(defn setup []
  (q/background 255)
  (q/smooth)
  (q/stroke-weight 1)
  (q/fill 150 50)
  (draw-circles)
  (q/set-state! :mouse-position (atom [0 0])))

(q/defsketch gen-art-30
  :title "Random Clicked Circles"
  :setup setup
  :mouse-moved mouse-moved
  :mouse-pressed capture-click
  :size [500 300])
