; SISTEMA CONTROLADOR DE UNA VIVIENDA INTELIGENTE


; Variable global que muestra la temperatura actual dentro de la vivienda
(defglobal ?*temperatura* = 20)


; Función que se aplica cuando está bajando la temperatura.
; Comprueba si hay menos de 20 grados para que el calefactor suba a 1 grado más
; la temperatura.
(deffunction bajando () 
	(if (< ?*temperatura* 20) 
	then (++ ?*temperatura*)
	      (printout t "Subiendo temperatura." crlf)
	     (printout t "Temperatura actual: "?*temperatura* " grados" crlf)
	)
)


; Función que se aplica cuando está subiendo la temperatura.
; Comprueba si hay más de 20 grados para que el calefactor baje a 1 grado más
; la temperatura.
(deffunction subiendo () 
	(if (> ?*temperatura* 20) 
	then (-- ?*temperatura*)
	      (printout t "Bajando temperatura." crlf)
		(printout t "Temperatura actual: "?*temperatura* " grados" crlf)
	)
)



; Regla que cierra la puerta si está abierta y la vivienda vacía.
(defrule puertaCerrada
	(and
		(vivienda vacia)
		(puerta abierta)
	)
	=>
	(printout t "Cerrar la puerta." crlf)
	(retract-string "(puerta abierta)")
	(assert (puerta cerrada))
)


; Regla que conecta la alarma si está desconectada y la vivienda vacía.
(defrule alarmaConectada
	(and
		(vivienda vacia)
		(alarma desconectada)
	)
	=>
	(printout t "Conectar la alarma." crlf)
	(retract-string "(alarma desconectada)")
	(assert (alarma conectada))
)


; Regla que apaga las luces si están encendidas y la vivienda vacía.
(defrule apagarLuces
	(and
		(vivienda vacia)
		(luz encendida)
	)
	=>
	(printout t "La vivienda esta vacia. Las luces deben estar apagadas." crlf)
	(retract-string "(luz encendida)")
	(assert (luz apagada))
)


; Regla que llama a la policía cuando la alarma está activada.
(defrule llamarPolicia
	(alarma activada)
	=>
	(printout t "Llamar a la policia." crlf)
	(retract-string "(alarma activada)")
	(assert (alarma conectada))
)


; Regla que enciende la nevera si está apagada y con la puerta cerrada.
(defrule neveraApagada
	(and
		(nevera apagada)
		(puerta-nevera cerrada)
	)
	=>
	(printout t "Encender nevera." crlf)
	(retract-string "(nevera apagada)")
	(assert (nevera encendida))
)

; Regla que cierra la puerta de la nevera si está abierta y con la nevera funcionando.
(defrule neveraEncendida
	(and
		(nevera encendida)
		(puerta-nevera abierta)
	)
	=>
	(printout t "Cerrar puerta nevera." crlf)
	(retract-string "(puerta-nevera abierta)")
	(assert (puerta-nevera cerrada))
)

; Regla que cierra la puerta del horno si está abierta y este funcionando.
(defrule hornoEncendido
	(and
		(horno encendido)
		(puerta-horno abierta)
	)
	=>
	(printout t "Cerrar puerta horno." crlf)
	(retract-string "(puerta-horno abierta)")
	(assert (puerta-horno cerrada))
)
