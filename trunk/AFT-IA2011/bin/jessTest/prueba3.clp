(deftemplate par (slot valor (type INTEGER)))
(deftemplate impar (slot valor (type INTEGER)))
(deftemplate numero (slot valor (type NUMBER)))


(defrule numero-par
    ?h <- (numero (valor ?v))
    (test (and (integerp ?v ) (= 0 (mod ?v 2))))
    =>
    (retract ?h)
    (assert (par (valor ?v)))
    )

(defrule numero-impar
    ?v <- (numero (valor ?v))
    (test (and (integerp ?v) (= 1 (mod ?v 2))))
    =>
    (retract ?h)
    (assert (impar (valor ?v)))
    )
