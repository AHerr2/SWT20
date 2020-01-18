(def ^:constant col-left (list "1" "4" "7"))
(def ^:constant col-center (list "2" "5" "8"))
(def ^:constant col-right (list "3" "6" "9"))
(def ^:constant row-above (list "1" "2" "3"))
(def ^:constant row-center (list "4" "5" "6"))
(def ^:constant row-below (list "7" "8" "9"))
(def ^:constant diagonal-left (list "1" "5" "9"))
(def ^:constant diagonal-right (list "3" "5" "7"))
(def ^:constant winning-lists (list col-left col-center col-right row-above row-center row-below diagonal-left diagonal-right))


(defn print-field [field]
	(doseq [i (range 0 (count field))]
		(doseq [j (range 0 (count (nth field i)))] 
  		(print (nth (nth field i) j))
		)
		(println)
	)
	(println)
)

(defn place-field [field pos symbol]
	(case pos "1" (aset (nth field 0) 0 symbol)
	"2" (aset (nth field 0) 2 symbol)
	"3" (aset (nth field 0) 4 symbol)
	"4" (aset (nth field 2) 0 symbol)
	"5" (aset (nth field 2) 2 symbol)
	"6" (aset (nth field 2) 4 symbol)
	"7" (aset (nth field 4) 0 symbol)
	"8" (aset (nth field 4) 2 symbol)
	"9" (aset (nth field 4) 4 symbol)
	) 

)

(defn kack [tada tudu]

	

)


(defn search-winner [my-pos com-pos]
	(cond	
	(.contains (into () (doall (map #(every? (fn[n](.contains my-pos n)) %) winning-lists))), true) 1
	(.contains (into () (doall (map #(every? (fn[n](.contains com-pos n)) %) winning-lists))), true) 2
	(>= (+ (count my-pos) (count com-pos)) 9) 0
	:else -1
	)
)

(defn make-com-choice [my-pos com-pos]
	(def ^:dynamic com-choice (+ (rand-int 9) 1))
	(while (or (.contains my-pos (str com-choice)) (.contains com-pos (str com-choice)))
		(def ^:dynamic com-choice (+ (rand-int 9) 1))
	)
	com-choice
	
)

(defn make-com-move [go-on my-pos com-pos]

	(if (< go-on 0)		
		(make-com-choice my-pos com-pos)		
		go-on
	)

)


(defn main []
	(def ^:dynamic my-pos (list))
	(def ^:dynamic com-pos (list))
	(def ^:dynamic field (to-array-2d [[" ","|"," ","|"," "]["-","-","-","-","-"][" ","|"," ","|"," "]["-","-","-","-","-"][" ", "|"," ", "|"," "]]))
	(def ^:dynamic go-on -1)
	(print-field field)

	(while (< go-on 0)
		(println "Place your field number from 1-9.")
		(def ^:dynamic answer (read-line))
		(while (or (.contains my-pos answer) (.contains com-pos answer))
			(println "This position is taken")
			(def ^:dynamic answer(read-line))
		)
		(def ^:dynamic my-pos (conj my-pos answer))
		(place-field field answer "X")
		(print-field field)		
		(def ^:dynamic go-on (search-winner my-pos com-pos))
		
		(def ^:dynamic com-choice (make-com-move go-on my-pos com-pos))
		(if (> com-choice 0)
				(do
				(def ^:dynamic com-pos (conj com-pos (str com-choice)))
				(place-field field (str com-choice) "O")
				(print-field field)
				(def ^:dynamic go-on (search-winner my-pos com-pos))
				)
				
		)

		
		
		




	)

	(cond
	(= go-on 0) (println "Both lose")
	(= go-on 1) (println "You won")
	(= go-on 2) (println "You lose")
	)


)


(main) 


