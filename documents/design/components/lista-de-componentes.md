# Lista de Clases

## Input y Output

- ### class UserInputParser
	- Una clase estáticapara recibir el input del usuario, y parsearlo asegurándose de que se consiguen valores esperables.
	- Métodos de Ejemplo:
		- getUserInputAsInteger()
		- getUserInputAsChar()
		- getUserInputAsString()
- ### class MenuView
	- Clase estática que se encarga de mostrar el menú y pedir el input del jugador.
- ### class Stats
	- Clase estática que se encarga de guardar las estadísticas de la partida.
		- getPlaceStats()
		- getTreasureStatsByRegion()
		- getTreasuresStatsByType()
- ### class Chance
	- Clase estática que se encarga del cálculo de las probabilidades.
		- generateExcavationTreasureList()
- ### class Repository
	- Clase estática que contiene todos los tesoros, regiones, habilidades de la partida.
		- getTreasure()
		- getRegion()
		- getSkill()