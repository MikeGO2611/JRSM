# Manual Técnico de Arquitectura — Archaios

## Estructura de recursos, configuración y persistencia

### Carpeta `config/`
Almacena la configuración externa necesaria para conectar la aplicación con el servidor del museo.
* **Zeraf.conf**: Fichero JSON que define la URL del servidor remoto y el identificador del grupo de trabajo.

### Carpeta `input/treasures/`
Contiene los archivos de datos iniciales con los tesoros disponibles en cada región del mundo.
* **EGP.json, GRC.json, IBR.json, PTR.json, ROM.json y SUM.json**: Catálogos en formato JSON leídos por `TreasureJSON` al arrancar el programa para poblar las colecciones de tesoros de cada país.

### Carpeta `lib/`
Reúne las librerías externas empaquetadas (`.jar`) de las que depende la compilación y ejecución del proyecto.
* **gson-2.15.0.jar** *(y `gson-2.15.0-javadoc.jar`)*: Librería de Google empleada para la serialización y deserialización de archivos JSON de tesoros y partidas guardadas.
* **Terra.v1.0.0.jar**: Librería externa que proporciona las clases de `Zeraf` para la comunicación y envío de tesoros al museo.

### Carpeta `saves/`
Directorio destinado a almacenar el estado persistente de las partidas guardadas por el jugador.
* **`<nombrePartida>.save`**: Archivos generados en formato JSON por `JsonManager` que guardan el UID, los puntos, los arqueólogos con sus habilidades/licencias y las estadísticas acumuladas.

### Carpeta `log/`
Centraliza el registro histórico de eventos de juego y la traza de errores de ejecución.
* **errors.log**: Fichero común donde `LogManager` registra con marca temporal las excepciones y fallos de entrada/salida.
* **`register/<nombrePartida>.log`**: Archivos de registro individuales por partida que anotan cronológicamente el inicio de sesión y los códigos de los tesoros descubiertos.

### Carpeta `backup/`
Almacena copias de seguridad locales generadas durante el intercambio de datos con el módulo del museo.
* **`<idGrupo>_<idUsuario>.bkp`**: Ficheros de respaldo codificados que conservan los datos de tesoros enviados por el jugador.

---

## Estructura del código fuente (`src/`)

Contiene todo el código fuente Java de la aplicación, organizado de forma modular en paquetes bajo la raíz `org.archaios`. A continuación se detalla la responsabilidad de cada paquete y de las clases, interfaces y enumeraciones que lo componen.

### Paquete `org.archaios.commons.input`
Contiene las utilidades responsables de la captura interactiva, saneamiento y validación de las entradas por consola del usuario.
* **UserInput**: Centraliza la lectura de datos por teclado mediante métodos estáticos que validan enteros, rangos numéricos, cadenas de texto y nombres únicos de arqueólogos. Evita entradas erróneas antes de pasarlas al resto del sistema.

### Paquete `org.archaios.commons.json`
Agrupa las clases encargadas de la serialización y deserialización del estado de la partida en formato JSON mediante la librería GSON.
* **JsonManager**: Gestiona la escritura y lectura de los archivos de guardado (`.save`) en el directorio `./saves`, registrando cualquier error de entrada/salida en el sistema de logs.
* **SaveJObject**: Encapsula todos los datos persistentes de una partida (UID, nombre, puntos, plantilla de arqueólogos y estadísticas internas mediante `SaveStats`) con anotaciones `@SerializedName` para su volcado a JSON.
* **SkillAdapter**: Implementa un adaptador personalizado de GSON (`JsonSerializer` y `JsonDeserializer`) para guardar las habilidades por su nombre y reconstruir sus instancias concretas al cargar una partida.

### Paquete `org.archaios.commons.logs`
Gestiona el registro cronológico de eventos de partida y la captura de errores en archivos de texto.
* **LogManager**: Crea y administra el archivo de registro individual de cada partida (`./log/register/`) y el fichero común de incidencias (`./log/errors.log`), anteponiendo la fecha y hora exacta en cada línea.

### Paquete `org.archaios.commons.operations`
Define la interfaz base para desacoplar y estandarizar las operaciones ejecutables desde los menús del sistema.
* **IOperation**: Interfaz que define el contrato de cualquier opción del menú mediante los métodos `getName()` y `operation()`. Permite que los menús invoquen funcionalidades polimórficamente sin conocer su implementación interna.

### Paquete `org.archaios.commons.repositories`
Agrupa los repositorios genéricos y especializados en memoria para indexar y consultar las entidades centrales del juego.
* **ICode**: Interfaz que obliga a las entidades almacenables a exponer un código identificador único mediante `getCode()`.
* **Repository**: Clase genérica base que implementa el almacenamiento en listas y la recuperación de elementos a partir de su código.
* **Regions**: Repositorio especializado que instancia y registra todas las regiones del juego (Grecia, Egipto, Iberia, Petra, Roma y Sumeria).
* **Skills**: Repositorio especializado en habilidades que almacena el catálogo completo y ofrece la selección aleatoria de destrezas compatibles.
* **Terrains**: Clase repositorio reservada para la futura gestión centralizada de terrenos en el sistema.
* **Treasures**: Repositorio especializado encargado de almacenar y recuperar el inventario global de tesoros del juego.

### Paquete `org.archaios.commons.statistics`
Centraliza el registro y acumulación de las métricas de la partida, el saldo del jugador y el desempeño en expediciones.
* **Stats**: Clase estática que gestiona la plantilla de arqueólogos contratados, los puntos disponibles y el histórico de entradas estadísticas por región.
* **TerrainStatEntry**: Modela el registro estadístico de una región concreta, contabilizando sus visitas, excavaciones exitosas y los tesoros recuperados en ella.

### Paquete `org.archaios.excavation`
Aloja el motor de simulación de la excavación, los tipos y formas de terreno, la cuadrícula interactiva y el ciclo de turnos.
* **ETerrainShape**: Enumeración que define las disposiciones geométricas del tablero (fila, horizontal, columna, vertical y cuadrado) junto con sus dimensiones según el tamaño elegido.
* **ETerrainSize**: Enumeración que especifica los tamaños base de cuadrícula, el número de filas y columnas iniciales y la bonificación de experiencia asociada.
* **ETerrainType**: Enumeración de los tipos de suelo excavables (tierra básica `·`, terreno compacto `-`, arena `~` y piedra `^`), indicando su rango de coste en acciones y su capa subyacente.
* **Grid**: Modela la matriz bidimensional de celdas gestionando las capas de terreno, costes por casilla, demolición de piedras, control de celdas ya excavadas y pistas visuales.
* **Excavation**: Orquesta una expedición completa controlando los turnos restantes, el cálculo dinámico de costes, la distribución de tesoros, el envío de resultados a Zeraf y la entrega de recompensas.

### Paquete `org.archaios.helpers`
Suministra clases auxiliares con rutinas comunes de formateo de texto, cálculos matemáticos y análisis de identificadores.
* **CodeHelper**: Proporciona utilidades estáticas para descomponer los códigos alfanuméricos de los tesoros y extraer su región, tipo, número y parte.
* **UtilsHelper**: Reúne métodos de apoyo para capitalizar textos, calcular porcentajes y generar combinaciones aleatorias de índices.

### Paquete `org.archaios.main`
Contiene el punto de entrada de la aplicación y la coordinación del ciclo de vida completo de una sesión de juego.
* **Archaios**: Clase principal que ejecuta la preinicialización de repositorios, la carga o creación de partidas guardadas, la conexión con la librería externa `Zeraf`, el registro en logs y el guardado automático al finalizar.

### Paquete `org.archaios.menu`
Contiene el controlador principal de la consola y la utilidad para renderizar interfaces de texto uniformes.
* **Menu**: Punto de entrada de la interfaz de usuario que agrupa los módulos principales (arqueólogos, habilidades, licencias, atlas, catálogo, excavación y estadísticas) en el bucle principal.
* **MenuGenerator**: Utilidad estática para maquetar menús en consola generando encabezados, divisores visuales y lectura validada de opciones.

### Paquete `org.archaios.menu.archeologist`
Reúne los menús encargados de la contratación, consulta y despido de arqueólogos de la plantilla.
* **ArcheologistMenu**: Menú contenedor que expone las distintas operaciones administrativas sobre los arqueólogos.
* **ArcheologistMenuUtils**: Clase de apoyo que centraliza la selección interactiva de un arqueólogo de la plantilla actual.
* **HireArcheologists**: Menú que genera candidatos y permite contratar nuevos arqueólogos descontando los puntos correspondientes.
* **LicenseArcheologist**: Submenú que permite licenciar (despedir) a un arqueólogo a cambio de puntos, siempre que quede al menos uno en la plantilla.
* **ShowArcheologists**: Muestra en consola la ficha completa de los arqueólogos con su nivel, experiencia, habilidades y terrenos disponibles.

### Paquete `org.archaios.menu.atlas`
Gestiona la navegación interactiva por el atlas de regiones disponibles en el repositorio.
* **AtlasMenu**: Lista las regiones registradas en `Archaios.regionsRepository` y permite inspeccionar todos los tesoros que pueden hallarse en cada una.

### Paquete `org.archaios.menu.excavation`
Controla el flujo de configuración previa a una expedición y el lanzamiento de la partida.
* **ExcavateMenu**: Solicita primero el arqueólogo, filtra las regiones disponibles (básicas más difíciles con licencia), pide el tamaño del terreno y lanza `Excavation` con una forma válida.

### Paquete `org.archaios.menu.license`
Implementa la interfaz de adquisición de licencias para habilitar expediciones en regiones difíciles.
* **LicenseMenu**: Consulta en el repositorio las regiones marcadas con `ITerDif` que el arqueólogo aún no posee, valida el coste progresivo en puntos y registra la licencia comprada.

### Paquete `org.archaios.menu.skill`
Agrupa los menús de consulta y exploración de habilidades organizadas según su categoría funcional.
* **SkillMenu**: Menú raíz de habilidades que canaliza la navegación hacia los listados específicos de cada tipo.
* **ExcavationSkillMenu**: Lista y describe las habilidades orientadas a otorgar turnos directos o bonificaciones porcentuales de excavación.
* **SpecialistSkillMenu**: Muestra las habilidades de especialista en terrenos difíciles, reducción de costes de excavación y demolición inicial de piedras.
* **TerrainSkillMenu**: Presenta el listado de habilidades que desbloquean nuevas formas geométricas para la cuadrícula.
* **TreasureSkillMenu**: Exhibe las habilidades enfocadas en garantizar la aparición de tesoros adicionales o mejorar sus probabilidades de rareza.

### Paquete `org.archaios.menu.stats`
Centraliza los menús de visualización del rendimiento histórico, piezas recuperadas y estadísticas territoriales.
* **StatsMenu**: Menú distribuidor que permite consultar las métricas por regiones y los distintos desgloses de tesoros encontrados.
* **StatsRegionsMenu**: Imprime el informe de visitas realizadas y tesoros excavados en cada una de las regiones visitadas.
* **StatsTreasureMenu**: Submenú que bifurca la consulta de antigüedades recuperadas según su región de procedencia o su tipología.
* **StatsTreasureByRegionMenu**: Muestra un listado detallado de las piezas arqueológicas desenterradas agrupadas por región.
* **StatsTreasureByTypeMenu**: Clasifica y muestra las piezas históricas encontradas según su tipo arqueológico.
* **StatsTreasureUtils**: Clase utilitaria con rutinas compartidas para imprimir listados formateados de tesoros.

### Paquete `org.archaios.menu.treasure`
Maneja la visualización del registro general de tesoros del juego.
* **TreasureCatalogMenu**: Muestra el catálogo integral de antigüedades, detallando su rareza, descripción, código y valor en puntos.

### Paquete `org.archaios.people`
Modela a los arqueólogos como entidades dinámicas con progresión, habilidades y permisos regionales.
* **Archeologist**: Representa a un arqueólogo gestionando su nivel, experiencia, habilidades activas, licencias adquiridas y costes progresivos. Expone además modificadores directos de acciones, costes por terreno y demolición para la fase de excavación.

### Paquete `org.archaios.regions`
Define el modelo base de las regiones geográficas y su colección global de consulta.
* **Region**: Clase abstracta que encapsula el código, el nombre y la colección de tesoros de una región, ofreciendo consultas filtradas o aleatorias por rareza.
* **Atlas**: Clase utilitaria que mantiene un mapa estático de instancias de regiones y permite recuperarlas por nombre o como lista completa.

### Paquete `org.archaios.regions.Countries`
Contiene las implementaciones concretas de cada región del mundo, la interfaz de regiones difíciles y la carga de tesoros desde JSON.
* **ITerDif**: Interfaz que identifica a las regiones difíciles e impone la declaración de su expansión y los porcentajes de aparición de cada tipo de terreno.
* **TreasureJSON**: Utilidad encargada de leer y deserializar mediante GSON los ficheros JSON de tesoros ubicados en `./input/treasures/`.
* **Greece**: Región básica que modela Grecia (`GRC`) cargando su catálogo de tesoros desde `GRC.json`.
* **Iberia**: Región básica que modela Iberia (`IBR`) cargando su catálogo de tesoros desde `IBR.json`.
* **Rome**: Región básica que modela Roma (`ROM`) cargando su catálogo de tesoros desde `ROM.json`.
* **Egypt**: Región difícil (`ITerDif`) que modela Egipto (`EGP`), carga `EGP.json` y define su distribución de arena, tierra y terreno compacto.
* **Petra**: Región difícil (`ITerDif`) que modela Petra (`PTR`), carga `PTR.json` y define su distribución predominante de piedra y arena.
* **Sumeria**: Región difícil (`ITerDif`) que modela Sumeria (`SUM`), carga `SUM.json` y define su distribución mixta de arena, piedra y tierra.

### Paquete `org.archaios.skills`
Modela la jerarquía polimórfica de habilidades, sus efectos sobre el juego y las reglas de compatibilidad.
* **ESkillType**: Enumeración que clasifica las habilidades en excavación, especialista, revelación, terreno y tesoro.
* **Skill**: Clase abstracta base que define el nombre, descripción, tipo, requisitos previos e incompatibilidades de cada destreza, vinculada a `SkillAdapter` para su guardado en JSON.
* **ExcavationSkill**: Especialización que proporciona turnos extra directos o multiplicadores porcentuales al total de acciones.
* **ExpertSkill**: Especialización que fija costes reducidos en terrenos difíciles concretos y permite demoler piedras antes de comenzar a excavar.
* **RevelationSkill**: Especialización que desvela información oculta al inicio de la excavación marcando casillas vacías o ubicaciones de tesoros.
* **TerrainSkill**: Especialización que desbloquea nuevas formas geométricas de cuadrícula para el arqueólogo.
* **TreasureSkill**: Especialización que garantiza la generación de tesoros adicionales o de rarezas concretas en el yacimiento.
* **SkillService**: Servicio encargado de seleccionar ternas de habilidades válidas al subir de nivel respetando dependencias e incompatibilidades.

### Paquete `org.archaios.treasures`
Define los tipos, rarezas y atributos de los artefactos históricos que pueden desenterrarse en los yacimientos.
* **ETreasureRarity**: Enumeración que clasifica las piezas en común, infrecuente o rara junto con su código representativo.
* **ETreasureType**: Enumeración que agrupa los tesoros por su tipología formal y proporciona los códigos de clasificación.
* **Treasure**: Modela un artefacto histórico almacenando su nombre, código, descripción, tipo, rareza, número de partes y puntuación.