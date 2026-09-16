# Componentes

Para los componentes se seguirá un patrón de arquitectura Model-View-Controller.
## Input y Output
- class InputParser
    - Una clase singletón para recibir el input del usuario, y parsearlo asegurándose de que se consiguen valores esperables.
## Conexión
- class DatabaseConnector
    - Una clase singletón para gestionar la conexión con la base de datos del museo.
## GameModel
- class GameModel
    - Clase singletón que representa el modelo estático del juego que a su vez está compuesto de:
        - class TreasureModel
            - Contiene los tesoros existentes.
        - class AtlasModel
            - Contiene todas las regiones existentes.
## GameState
- class GameState
    - Clase singletón que contiene el estado del juego, a su vez está compuesto de:
        - class ArcheologistState
            - Contiene todos los arqueólogos del jugador.
        - class TreasureState
            - Contiene todos los tesoros obtenidos por el jugador.
