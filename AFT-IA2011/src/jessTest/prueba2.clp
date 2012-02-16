(deffacts entrada "Los datos obtenidos del programa"
    (dato1 (fetch dato1))
    (dato2 (fetch dato2))
    )

(defrule suma "Suma losaaa datos previamente cargados"
    (dato1 ?n1) (dato2 ?n2)
    =>
    (assert (resultado (+ ?n1 ?n2)))
    )

(defrule guarda "Guarda el resultado y termina programa"
    (resultado ?r)
    =>
    (store resultado ?r)
    (halt) 
    )