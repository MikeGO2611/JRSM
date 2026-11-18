# Manual Técnico de Arquitectura — Archaios

### Paquete `org.archaios.commons.input`
Contiene las clases responsables de la captura interactiva, saneamiento y validación de las entradas por consola del usuario.
* **UserInput**: Centraliza la lectura de datos por teclado mediante métodos estáticos que parsean enteros, rangos numéricos y cadenas de texto. Valida que las respuestas del jugador sean correctas antes de retornarlas al sistema.

### Paquete `org.archaios.commons.operations`
Define la interfaz base para desacoplar y estandarizar las operaciones ejecutables desde los menús del sistema.
* **IOperation**: Interfaz que define el contrato de cualquier opción del menú mediante los métodos `getName()` y `operation()`. Permite que los generadores de menús invoquen funcionalidades polimórficamente sin conocer su implementación interna.

### Paquete `org.archaios.commons.repositories`
Agrupa los repositorios genéricos y especializados en memoria para indexar y consultar las entidades centrales del juego.
* **ICode**: Interfaz marcadora que obliga a las entidades almacenables a exponer un código alfanumérico único para su identificación.
* **Repository**: Clase genérica base que implementa el almacenamiento en listas y la búsqueda de elementos a partir de su código identificador.
* **Regions**: Repositorio especializado en el registro y consulta del catálogo de regiones geográficas disponibles.
* **Skills**: Repositorio especializado en habilidades que implementa la lógica de selección de destrezas compatibles y aleatorias.
* **Treasures**: Repositorio especializado encargado de almacenar y recuperar el inventario global de tesoros del juego.

### Paquete `org.archaios.commons.statistics`
Centraliza el registro, acumulación y persistencia de las métricas de la partida y el desempeño en expediciones.
* **Stats**: Clase estática que gestiona la nómina de arqueólogos contratados, el saldo global de puntos y el histórico de excavaciones.
* **TerrainStatEntry**: Modela la entrada estadística de una región registrando las visitas realizadas y el listado de tesoros rescatados en ella.

### Paquete `org.archaios.excavation`
Aloja el motor de simulación de la excavación, la matriz visual del terreno y el ciclo de turnos de la expedición.
* **ETerrainShape**: Enumeración que define las siluetas geométricas utilizables en el tablero, como cuadradas o piramidales.
* **ETerrainSize**: Enumeración que especifica las dimensiones de la cuadrícula, los turnos base calculados y los puntos de experiencia asociados.
* **Terrain**: Encapsula la configuración física de un terreno combinando su tamaño, geometría, dimensiones y cuotas mínimas de tesoros.
* **Grid**: Modela la matriz bidimensional de celdas, gestionando el desenterramiento, el renderizado de casillas y las pistas visuales.
* **Excavation**: Orquesta la partida de excavación controlando el gasto de acciones por terreno, la colocación de tesoros y la entrega de recompensas.

### Paquete `org.archaios.helpers`
Suministra clases auxiliares con rutinas comunes de formateo de texto, manipulación matemática y análisis de identificadores.
* **CodeHelper**: Proporciona utilidades estáticas para descomponer los códigos alfanuméricos de tesoros y extraer su región, tipo y número.
* **UtilsHelper**: Reúne métodos de apoyo para capitalizar textos, calcular porcentajes y generar combinaciones aleatorias de índices.

### Paquete `org.archaios.main`
Contiene la clase principal de arranque de la aplicación y la gestión de estados globales del museo.
* **Archaios**: Clase de entrada que contiene el método `main`, inicializa los repositorios de datos y transfiere el control al menú inicial.
* **MuseumState**: Enumeración que modela los diferentes estados operativos y de progreso por los que transita el museo.

### Paquete `org.archaios.menu`
Contiene el controlador principal de la consola y la utilidad para renderizar interfaces de texto uniformes.
* **Menu**: Punto de entrada de la interfaz de usuario que enlaza las diferentes operaciones principales con el bucle de ejecución.
* **MenuGenerator**: Utilidad estática para maquetar menús en consola generando encabezados, divisores estéticos y lectura de opciones protegida.

### Paquete `org.archaios.menu.archeologist`
Reúne los menús encargados de la contratación, visualización, despido y licenciamiento de arqueólogos.
* **ArcheologistMenu**: Menú contenedor que expone las acciones disponibles para administrar la plantilla de arqueólogos.
* **ArcheologistMenuUtils**: Clase de soporte que centraliza la lógica interactiva para que el usuario escoja un arqueólogo disponible.
* **HireArcheologists**: Menú que genera candidatos aleatorios y permite contratar nuevos arqueólogos descontando puntos del jugador.
* **LicenseArcheologist**: Submenú destinado a tramitar y asignar licencias de excavación a un arqueólogo seleccionado.
* **ShowArcheologists**: Presenta en consola el perfil detallado, nivel actual, experiencia y catálogo de habilidades de cada arqueólogo.

### Paquete `org.archaios.menu.atlas`
Gestiona la navegación interactiva por el atlas histórico de regiones del mundo.
* **AtlasMenu**: Muestra el catálogo de regiones disponibles permitiendo explorar su contexto histórico y tesoros característicos.

### Paquete `org.archaios.menu.excavation`
Controla el flujo de configuración previa a una expedición y el inicio de la partida.
* **ExcavateMenu**: Guía al usuario en la preparación de la expedición solicitando el arqueólogo, la región, el tamaño y la forma del terreno.

### Paquete `org.archaios.menu.license`
Implementa la interfaz de adquisición de licencias para habilitar expediciones a regiones complejas.
* **LicenseMenu**: Gestiona la compra de licencias para regiones difíciles validando el saldo de puntos y actualizando el perfil del arqueólogo.

### Paquete `org.archaios.menu.skill`
Agrupa los menús de consulta y exploración de habilidades divididas por su categoría funcional.
* **SkillMenu**: Menú raíz de destrezas que canaliza la navegación hacia los listados específicos de cada tipo de habilidad.
* **ExcavationSkillMenu**: Lista y describe las habilidades orientadas a otorgar turnos directos o bonificaciones porcentuales de excavación.
* **SpecialistSkillMenu**: Muestra las habilidades de especialista en terrenos difíciles, reducción de costes de pala y demolición de rocas.
* **TerrainSkillMenu**: Presenta el listado de habilidades que desbloquean geometrías avanzadas para el tablero de excavación.
* **TreasureSkillMenu**: Exhibe las habilidades enfocadas en garantizar la aparición de tesoros de mayor rareza o cantidad.

### Paquete `org.archaios.menu.stats`
Centraliza los menús de visualización del rendimiento histórico, piezas recuperadas y estadísticas territoriales.
* **StatsMenu**: Menú distribuidor que permite consultar métricas globales, desgloses por regiones y clasificaciones de tesoros.
* **StatsRegionsMenu**: Imprime el informe del número de visitas y rendimiento obtenido en cada una de las regiones excavadas.
* **StatsTreasureMenu**: Submenú que bifurca la consulta del inventario de antigüedades según su origen geográfico o categoría funcional.
* **StatsTreasureByRegionMenu**: Genera un informe detallado con las piezas arqueológicas desenterradas agrupadas por su región de procedencia.
* **StatsTreasureByTypeMenu**: Clasifica y muestra las piezas históricas encontradas según su tipo arqueológico (cerámica, joyas, estatuas).
* **StatsTreasureUtils**: Clase utilitaria con rutinas compartidas para imprimir listados tabulados y formateados de tesoros.

### Paquete `org.archaios.menu.treasure`
Maneja la visualización del registro general de tesoros y colecciones del museo.
* **TreasureCatalogMenu**: Muestra el catálogo integral de antigüedades desenterradas, detallando su rareza, descripción y puntos de valor.

### Paquete `org.archaios.people`
Modela a los arqueólogos como entidades dinámicas con experiencia acumulable, habilidades y permisos de viaje.
* **Archeologist**: Representa a un arqueólogo controlando su nivel, puntos de experiencia, habilidades activas y licencias adquiridas. Calcula la progresión de costes de sus licencias y suministra modificadores directos a la fase de excavación.

### Paquete `org.archaios.regions` y `org.archaios.regions.Countries`
Define las regiones arqueológicas del mundo donde se desarrollan las expediciones.
* **Region**: Clase base que encapsula las propiedades culturales de una zona, su código, cuotas de terreno y colección de tesoros asociados.
* **Greece**: Especialización concreta que modela la región griega con sus tesoros característicos y configuración particular.

### Paquete `org.archaios.skills`
Modela la jerarquía polimórfica de habilidades, sus efectos sobre el juego y las reglas de compatibilidad.
* **ESkillType**: Enumeración que clasifica las habilidades en excavación, especialista, revelación, terreno y tesoro.
* **Skill**: Clase abstracta base que almacena el nombre, descripción, tipo, requisitos previos e incompatibilidades de cada destreza.
* **ExcavationSkill**: Especialización que proporciona turnos extra directos o multiplicadores porcentuales al total de intentos de excavación.
* **ExpertSkill**: Especialización que fija costes reducidos al picar tipos de suelo específicos y demuele piedras al inicio de la expedición.
* **RevelationSkill**: Especialización que revela información oculta al comienzo de la excavación marcando celdas vacías o tesoros.
* **TerrainSkill**: Especialización que desbloquea nuevas geometrías y formas de terreno para que el jugador pueda seleccionarlas.
* **TreasureSkill**: Especialización que garantiza la aparición de tesoros adicionales o de rarezas concretas en el yacimiento.
* **SkillService**: Servicio encargado de seleccionar ternas de habilidades válidas para un arqueólogo asegurando compatibilidades y dependencias.

### Paquete `org.archaios.treasures`
Define los tipos, rarezas y atributos de los artefactos que pueden descubrirse en los yacimientos.
* **ETreasureRarity**: Enumeración que clasifica las piezas en común, infrecuente o rara, asociando a cada una su letra representativa.
* **ETreasureType**: Enumeración que agrupa los tesoros por su tipología formal y suministra los prefijos para sus códigos.
* **Treasure**: Modela un artefacto histórico almacenando su nombre, código identificador, descripción, tipo, rareza y valor en puntos.

### Paquete `org.archaios.zeraf`
Encapsula la comunicación con el subsistema de persistencia del museo.
* **Zeraf**: Clase de enlace encargada de empaquetar y transferir la información de los tesoros encontrados hacia el módulo central de datos.