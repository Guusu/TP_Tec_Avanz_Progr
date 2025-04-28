# TP Integrador de Tecnicas Avanzadas de Programación
Repositorio de Gustavo Zurita para el TP de la materia Tecnicas Avanzadas de Programacion de la UP.
## Enunciado
Una pequeña sala de teatro independiente requiere que se gestione la venta de entradas para sus espectáculos. Cuentan con dos espacios una sala con capacidad para 70 personas y un anfiteatro a cielo abierto con una capacidad para 120 personas. La primera posee un costo variable en las entradas, las tipo A, tienen un costo del doble de las tipo B. El anfiteatro tiene precio único.

Para lo cual un usuario registrado en el sistema registra la siguiente información:

    Artista
    Fecha de la función
    Hora de la función
    Sala
    Precio de la entrada
    Duración
    Tipo de show (infantil, musical, obra de teatro)

En el proceso de carga se debe validar que no se superpongan los espectaculos en una misma fecha y hora, para permitir la carga de espectaculos un mismo día, debe haber una hora libre en la sala para su limpieza previa al inicio del proximo show

El sistema debe poder mostrar los espectaculos próximos a presentarse en la sala, así como los anteriores, en este ultimo caso por supuesto no debe poder permitir realizar la compra de entradas.

## Indice

1. [Diagrama de Clases del Sistema](#diagrama-de-clases-del-sistema)
2. [Modelo de Datos](#modelo-de-datos)
3. [Tecnologias a Utilizar](#detalle-de-las-tecnologías-a-utilizar)
4. [Modulos a Testear](#modulos-a-testear)
5. [Justificaciones](#justificacion-de-tecnologia-y-consideraciones) 
## Diagrama de clases del sistema.
![Diagrama de Clases](diagrams/ClassDiagram.png)
<details>
<summary ><strong> Ver Codigo PlanTUML</strong></summary>

```yaml
@startuml
Sala <|-- SalaCerrada
Sala <|-- Anfiteatro
Entrada o-- Usuario
Entrada o-- Espectaculo
Espectaculo o-- Sala

class Usuario {
  user : String
  nombre : String
  email : String
  password : String
}

class Espectaculo {
   artista : Artista
   fecha : Date
   duracionMinutos : Integer
   tipoShow : String 
   precioEntrada : float
   sala : Sala
}

class Sala{
  nombre : String 
  capacidad: Integer
  {abstract} float calcularPrecio(String TipoEntrada)
}
class SalaCerrada {
  float calcularPrecio(String tipoEntrada)
}
class Anfiteatro{
  float calcularPrecio(String tipoEntrada)
}
class Entrada{
  tipo : String
  precio : float
  usuario : Usuario
  espectaculo : Espectaculo
}

@enduml

```
</details>

[Descargar el código PlantUML](diagrams/classDiagram.wsd)

<span style="float: right;">
  [Volver al índice](#indice)
</span>


## Modelo de datos.
## Detalle de las tecnologías a utilizar.
## Modulos a Testear.
## Justificacion de Tecnologia y consideraciones.