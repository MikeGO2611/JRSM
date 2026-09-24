# Lista de Clases

Javier Búa Martínez, Miguel González Obenza, Sergio González Prego, Rubén Hernández Torres.

## Input y Output

- class UserInputParser

  - Una clase estática para recibir el input del usuario, y parsearlo asegurándose de que se consiguen valores esperables.
  - Métodos de Ejemplo:
    - getUserInputAsInteger()
    - getUserInputAsChar()
    - getUserInputAsString()

- class MenuView

  - Clase estática que se encarga de mostrar el menú y pedir el input del jugador.

- class Stats

  - Clase estática que se encarga de guardar las estadísticas de la partida.
    - getPlaceStats()
    - getTreasureStatsByRegion()
    - getTreasuresStatsByType()

- class Chance

  - Clase estática que se encarga del cálculo de las probabilidades.
    - generateExcavationTreasureList()

- class Repository

  - Clase estática que contiene todos los tesoros, regiones, habilidades de la partida.
    - getTreasure()
    - getRegion()
    - getSkill()

- class UtilsHelper

  - Clase estática que contiene funcionalidades para la transformación de strings, porcentajes y generación de índices de un array aleatorios.
    - ucfirst()
    - ucfirstAll()
    - getPercent()
    - getRandomIdxs()

- class CodeHelper

  - Clase estática que contiene funcionalidades relacionadas con los códigos de los tesoros. A partir del código se pueden obtener las regiones, los tipos, el número, etc...
    - getRegion()
    - getType()
    - getTreasureNumber()
    - getTreasurePart()
    - getFullTreasure()