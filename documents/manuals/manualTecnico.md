# Archaios - Manual Técnico

## Estructura de carpetas y recursos

### Carpeta `config/`
Contiene la configuración externa necesaria para conectar el programa con el servidor del museo.
* **Zeraf.conf**: Archivo JSON con la URL del servidor y el identificador del grupo.

### Carpeta `input/treasures/`
Guarda los ficheros de datos iniciales con los tesoros disponibles en cada región.
* **EGP.json, GRC.json, IBR.json, PTR.json, ROM.json y SUM.json**: Listados de tesoros en formato JSON que se cargan al iniciar el juego mediante `TreasureJSON`.

### Carpeta `lib/`
Almacena las librerías externas (`.jar`) necesarias para compilar y ejecutar el proyecto.
* **gson-2.15.0.jar** *(y `gson-2.15.0-javadoc.jar`)*: Librería usada para leer los ficheros de tesoros y guardar o cargar partidas en formato JSON.
* **Terra.v1.0.0.jar**: Librería externa que incluye las clases de `Zeraf` para enviar los tesoros encontrados al museo.

### Carpeta `saves/`
Carpeta donde se guardan las partidas del jugador.
* **`<nombrePartida>.save`**: Archivos JSON creados por `JsonManager` con el UID, el nombre de la partida, los puntos, los arqueólogos y las estadísticas.

### Carpeta `log/`
Almacena los registros de actividad de las partidas y los errores de ejecución.
* **errors.log**: Archivo común en el que `LogManager` anota con fecha y hora los errores de entrada/salida.
* **`register/<nombrePartida>.log`**: Registro individual de cada partida donde se apuntan el inicio de sesión y los códigos de los tesoros excavados.

### Carpeta `backup/`
Guarda copias de respaldo locales generadas al enviar datos al servidor del museo.
* **`<idGrupo>_<idUsuario>.bkp`**: Archivos de respaldo con los tesoros enviados por el usuario.

---

## Estructura del código fuente (`src/`)

Contiene el código Java del proyecto, dividido por paquetes dentro de `org.archaios`.

### Paquete `org.archaios.commons.input`
Incluye la clase encargada de leer y validar los datos que introduce el usuario por consola.
* **UserInput**: Métodos estáticos para pedir números enteros, valores dentro de un rango, texto y nombres de arqueólogos que no estén repetidos.

### Paquete `org.archaios.commons.json`
Reúne las clases dedicadas a guardar y cargar partidas en formato JSON con GSON.
* **JsonManager**: Escribe y lee los archivos `.save` dentro de `./saves` y registra en el log cualquier error de lectura o escritura.
* **SaveJObject**: Objeto que agrupa los datos que se guardan en el JSON (UID, nombre de partida, puntos, arqueólogos y la clase interna `SaveStats` para tesoros y visitas).
* **SkillAdapter**: Adaptador de GSON para guardar cada habilidad por su nombre y recuperar la instancia correspondiente desde `Archaios.skillRepository` al cargar.

### Paquete `org.archaios.commons.logs`
Gestiona el registro de eventos y errores en archivos de texto.
* **LogManager**: Crea los directorios y archivos de log al iniciar la partida y añade cada mensaje con la fecha y hora actuales.

### Paquete `org.archaios.commons.operations`
Define la interfaz común para todas las opciones ejecutables de los menús.
* **IOperation**: Interfaz con los métodos `getName()` y `operation()`, implementada por cada menú y submenú de la aplicación.

### Paquete `org.archaios.commons.repositories`
Contiene los repositorios en memoria para almacenar y buscar las entidades del juego por código.
* **ICode**: Interfaz que obliga a las clases almacenables a devolver un código único con `getCode()`.
* **Repository**: Clase genérica que guarda elementos en un `HashMap` usando su código y permite recuperarlos de uno en uno o en lista.
* **Regions**: Repositorio que crea y registra las seis regiones del juego (Grecia, Egipto, Iberia, Petra, Roma y Sumeria).
* **Skills**: Repositorio que inicializa todas las habilidades disponibles, define sus dependencias e incompatibilidades y permite pedir habilidades aleatorias compatibles.

### Paquete `org.archaios.commons.statistics`
Mantiene en memoria los datos generales de la partida y las estadísticas por región.
* **Stats**: Clase estática que almacena los puntos del jugador, su lista de arqueólogos y las entradas de estadísticas de cada región.
* **TerrainStatEntry**: Guarda para una región cuántas veces se ha visitado, el número total de tesoros excavados y la lista ordenada de tesoros encontrados.

### Paquete `org.archaios.excavation`
Contiene la lógica de la excavación, la cuadrícula y las enumeraciones del terreno.
* **ETerrainShape**: Enumerado con las formas posibles del tablero (fila, horizontal, columna, vertical y cuadrado) y sus dimensiones según el tamaño elegido.
* **ETerrainSize**: Enumerado con los tamaños de terreno (`SMALL`, `MEDIUM`, `LARGE`), su tamaño base y la experiencia extra al completar la excavación.
* **ETerrainType**: Enumerado con los tipos de suelo (`·` tierra, `-` terreno compacto, `~` arena y `^` piedra), su rango de coste en acciones y la capa inferior si tiene.
* **Grid**: Representa la cuadrícula de excavación, gestiona las capas de terreno, los costes, la demolición de piedras, las casillas ya excavadas, las pistas y el pintado por consola.
* **Excavation**: Controla una partida de excavación completa: calcula intentos, reparte tesoros, procesa las coordenadas del jugador, registra el log, aplica recompensas y envía los tesoros a Zeraf.

### Paquete `org.archaios.helpers`
Clases auxiliares con funciones de apoyo usadas en distintas partes del proyecto.
* **CodeHelper**: Métodos estáticos para separar un código de tesoro (`RRR_TTT_NNN_PPPP`) y sacar la región, el tipo, el número o la parte.
* **UtilsHelper**: Métodos de ayuda para poner mayúsculas iniciales, calcular porcentajes y generar índices aleatorios sin repetir.

### Paquete `org.archaios.main`
Contiene la clase principal de entrada al programa.
* **Archaios**: Arranca la aplicación desde `main`, inicializa los repositorios, permite cargar una partida o crear una nueva, crea el `LogManager`, conecta con `Zeraf` y guarda la partida.

### Paquete `org.archaios.menu`
Contiene el menú principal y las utilidades para mostrar menús por consola.
* **Menu**: Carga las siete secciones principales (arqueólogos, habilidades, licencias, atlas, catálogo, excavación y estadísticas) y lanza el bucle del menú.
* **MenuGenerator**: Clase estática para imprimir títulos, separadores y listas de opciones leyendo la selección del usuario.

### Paquete `org.archaios.menu.archeologist`
Agrupa los menús de gestión de la plantilla de arqueólogos.
* **ArcheologistMenu**: Submenú principal de arqueólogos con las opciones de ver, contratar y licenciar.
* **ArcheologistMenuUtils**: Utilidad para mostrar los arqueólogos disponibles y devolver el que seleccione el usuario.
* **HireArcheologists**: Permite gastar puntos para contratar un arqueólogo aleatorio (común, infrecuente o raro con habilidades iniciales) y asignarle un nombre único.
* **LicenseArcheologist**: Permite despedir a un arqueólogo a cambio de puntos, siempre que el jugador conserve al menos uno.
* **ShowArcheologists**: Muestra la información completa del arqueólogo seleccionado (nivel, experiencia, tamaños, formas, habilidades y licencias).

### Paquete `org.archaios.menu.atlas`
Gestiona la consulta de regiones y sus tesoros.
* **AtlasMenu**: Muestra las regiones del repositorio y permite entrar en cada una para ver sus tesoros y leer su descripción.

### Paquete `org.archaios.menu.excavation`
Controla la configuración previa antes de empezar a excavar.
* **ExcavateMenu**: Pide al jugador que elija arqueólogo, una región disponible según sus licencias y un tamaño de terreno, elige una forma válida y lanza `Excavation`.

### Paquete `org.archaios.menu.license`
Implementa la compra de licencias para regiones difíciles.
* **LicenseMenu**: Busca en `Archaios.regionsRepository` las regiones con `ITerDif` que el arqueólogo aún no tiene, comprueba los puntos disponibles y añade la licencia comprada.

### Paquete `org.archaios.menu.skill`
Contiene los menús de consulta de habilidades separadas por categoría.
* **SkillMenu**: Submenú principal que da acceso a las listas de habilidades de tesoro, excavación, terreno y especialista.
* **ExcavationSkillMenu**: Lista ordenada de las habilidades de excavación y revelación con su descripción y tipo.
* **SpecialistSkillMenu**: Lista ordenada de las habilidades de especialista en terrenos difíciles.
* **TerrainSkillMenu**: Lista ordenada de las habilidades que desbloquean formas de terreno.
* **TreasureSkillMenu**: Lista ordenada de las habilidades relacionadas con la aparición y rareza de tesoros.

### Paquete `org.archaios.menu.stats`
Agrupa los menús de consulta de estadísticas de la partida.
* **StatsMenu**: Submenú principal para elegir entre estadísticas de regiones o de tesoros.
* **StatsRegionsMenu**: Muestra por cada región visitada el número de visitas, los tesoros únicos encontrados y el total de tesoros excavados.
* **StatsTreasureMenu**: Submenú para ver los tesoros encontrados filtrados por región o por tipo.
* **StatsTreasureByRegionMenu**: Muestra los tesoros conseguidos agrupados por la región donde aparecieron.
* **StatsTreasureByTypeMenu**: Muestra todos los tesoros conseguidos agrupados por su `ETreasureType`.
* **StatsTreasureUtils**: Utilidad compartida para imprimir listas de tesoros agrupando las piezas con el mismo nombre.

### Paquete `org.archaios.menu.treasure`
Gestiona la vista del catálogo general de tesoros por región.
* **TreasureCatalogMenu**: Permite seleccionar una región del repositorio y muestra el listado con los nombres de todos sus tesoros.

### Paquete `org.archaios.people`
Contiene la clase que modela a los arqueólogos.
* **Archeologist**: Almacena el nombre, nivel, experiencia, habilidades, formas de terreno y licencias de cada arqueólogo. Calcula las subidas de nivel, el coste progresivo de licencias y los bonos que aplica durante una excavación.

### Paquete `org.archaios.regions`
Define la clase base de todas las regiones del juego.
* **Region**: Clase abstracta con el código, el nombre y la lista de tesoros de una región, además de métodos para obtener tesoros filtrados o aleatorios por rareza.

### Paquete `org.archaios.regions.Countries`
Contiene las regiones concretas, la interfaz de regiones difíciles y la carga de los JSON de tesoros.
* **ITerDif**: Interfaz que identifica las regiones difíciles y define `getExpansion()` y `getTerrainPercent(String)`.
* **TreasureJSON**: Lee los archivos JSON de `./input/treasures/` con GSON y los convierte en listas de objetos `Treasure`.
* **Greece**: Región básica de Grecia (`GRC`), carga sus tesoros desde `GRC.json`.
* **Iberia**: Región básica de Iberia (`IBR`), carga sus tesoros desde `IBR.json`.
* **Rome**: Región básica de Roma (`ROM`), carga sus tesoros desde `ROM.json`.
* **Egypt**: Región difícil (`ITerDif`) de Egipto (`EGP`), carga `EGP.json` y define los porcentajes de arena, tierra y terreno compacto.
* **Petra**: Región difícil (`ITerDif`) de Petra (`PTR`), carga `PTR.json` y define los porcentajes de piedra y arena.
* **Sumeria**: Región difícil (`ITerDif`) de Sumeria (`SUM`), carga `SUM.json` y define los porcentajes de arena, piedra y tierra.

### Paquete `org.archaios.skills`
Define la jerarquía de habilidades de los arqueólogos y su lógica de selección.
* **ESkillType**: Enumerado con los tipos de habilidad (tesoro, excavación, terreno y especialista).
* **Skill**: Clase abstracta base con nombre, descripción, tipo, dependencias e incompatibilidades, anotada con `@JsonAdapter(SkillAdapter.class)` para el guardado en JSON.
* **ExcavationSkill**: Habilidad que suma acciones fijas o aplica un multiplicador sobre los intentos iniciales.
* **ExpertSkill**: Habilidad de especialista que fija el coste de un tipo de terreno o rompe piedras antes de comenzar la excavación.
* **RevelationSkill**: Habilidad que revela al inicio casillas vacías o posiciones con tesoro.
* **TerrainSkill**: Habilidad que desbloquea nuevas formas de cuadrícula para el arqueólogo.
* **TreasureSkill**: Habilidad que garantiza tesoros adicionales aleatorios o de rarezas concretas.
* **SkillService**: Selecciona hasta tres habilidades válidas al subir de nivel respetando dependencias e incompatibilidades y priorizando mejoras.

### Paquete `org.archaios.treasures`
Modela los tesoros y sus enumerados de clasificación.
* **ETreasureRarity**: Enumerado con las rarezas de tesoro (`COMMON`, `UNCOMMON`, `RARE`) y su letra de código.
* **ETreasureType**: Enumerado con los tipos de tesoro (moneda, tesela, cuenta, joyería, cerámica, equipo, estela y fragmento) y su prefijo de tres letras.
* **Treasure**: Representa un tesoro con su nombre, descripción, código con número de parte, tipo, rareza, total de partes y puntos.