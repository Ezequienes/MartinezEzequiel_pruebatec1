**# Proyecto de Gestión de Empleados con JPA y Java**

Este proyecto es una aplicación de consola desarrollada en Java que permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre empleados utilizando JPA (Java Persistence API) para la gestión de la base de datos.

---

## **Requisitos Previos**

- **Java Development Kit (JDK) 8 o superior**
- **IDE (Eclipse, IntelliJ IDEA o NetBeans)**
- **MySQL Server y MySQL Workbench**
- **Dependencias JPA** (javax.persistence)

---

## **Estructura del Proyecto**

El proyecto está dividido en varios paquetes y clases, cada uno con una función específica:

### **1. Clase `Main`**
Esta clase es el punto de entrada de la aplicación.

**Ubicación:** `org.example.Main`

**Responsabilidades:**
- Crear un nuevo empleado y guardarlo en la base de datos.
- Modificar el salario de un empleado.
- Eliminar un empleado según su ID.
- Listar todos los empleados almacenados en la base de datos.

**Fragmento de código principal:**
```java
Empleado empleado1 = new Empleado(1L, "Pepe", "Melonez", "Oficinista", 1200, "30-04-2013");
ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();
controladoraPersistencia.crearEmpleado(empleado1);
```

---

### **2. Clase `Empleado`**
Define la entidad `Empleado`, que representa la estructura de la tabla en la base de datos.

**Ubicación:** `org.example.logica.Empleado`

**Atributos:**
- **id** (Long) - Identificador único de cada empleado.
- **nombre** (String) - Nombre del empleado.
- **apellido** (String) - Apellido del empleado.
- **cargo** (String) - Puesto o cargo del empleado.
- **salario** (int) - Salario del empleado.
- **fechaInicio** (String) - Fecha de inicio del empleado.

**Anotaciones importantes:**
- `@Entity`: Define esta clase como una entidad JPA.
- `@Id`: Marca el campo `id` como la clave primaria.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Indica que la clave primaria se generará automáticamente.

**Ejemplo de código:**
```java
@Entity
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nombre;
    protected String apellido;
    protected String cargo;
    protected int salario;
    protected String fechaInicio;
    // Getters, Setters y método toString()
}
```

---

### **3. Clase `ControladoraPersistencia`**
Gestiona la comunicación con la clase `EmpleadoJpaController` y proporciona métodos de alto nivel para las operaciones CRUD.

**Ubicación:** `org.example.persistencia.ControladoraPersistencia`

**Métodos principales:**
- **crearEmpleado(Empleado pers)**: Crea un nuevo empleado en la base de datos.
- **borrarEmpleado(Long id)**: Elimina un empleado de la base de datos por su ID.
- **modificarEmpleado(Empleado pers)**: Modifica la información de un empleado existente.
- **traerEmpleados()**: Recupera la lista de todos los empleados.

**Ejemplo de método:**
```java
public void crearEmpleado(Empleado pers) {
    empleJPA.create(pers);
}
```

---

### **4. Clase `EmpleadoJpaController`**
Contiene los métodos JPA que se comunican directamente con la base de datos.

**Ubicación:** `org.example.persistencia.EmpleadoJpaController`

**Métodos principales:**
- **create(Empleado empleado)**: Inserta un nuevo empleado en la base de datos.
- **edit(Empleado empleado)**: Actualiza los datos de un empleado existente.
- **destroy(Long id)**: Elimina un empleado por su ID.
- **findEmpleado(Long id)**: Busca un empleado por su ID.
- **findEmpleadoEntities()**: Devuelve la lista de todos los empleados.
- **getEmpleadoCount()**: Devuelve la cantidad de empleados en la base de datos.

**Ejemplo de método:**
```java
public void create(Empleado empleado) {
    EntityManager em = null;
    try {
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(empleado);
        em.getTransaction().commit();
    } finally {
        if (em != null) {
            em.close();
        }
    }
}
```

---

## **Instrucciones de Configuración**

1. **Configurar el archivo `persistence.xml`**:
   - Asegúrate de que tu archivo `persistence.xml` esté correctamente configurado con la unidad de persistencia (`jpaPU`) y la conexión a la base de datos MySQL.
   
   ```xml
   <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.1">
       <persistence-unit name="jpaPU">
           <class>org.example.logica.Empleado</class>
           <properties>
               <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
               <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/nombre_base_datos"/>
               <property name="javax.persistence.jdbc.user" value="tu_usuario"/>
               <property name="javax.persistence.jdbc.password" value="tu_contraseña"/>
           </properties>
       </persistence-unit>
   </persistence>
   ```

2. **Ejecutar la aplicación:**
   - Ejecuta la clase `Main` para ver el flujo completo de las operaciones CRUD.

3. **Ver los resultados:**
   - Revisa la consola para ver los registros de los empleados y los mensajes de confirmación de las operaciones CRUD.

---

## **Operaciones CRUD Disponibles**

| Operación  | Descripción                     |
|------------|----------------------------------|
| **Crear**  | Agrega un nuevo empleado a la base de datos. |
| **Leer**   | Muestra la lista de empleados.    |
| **Actualizar** | Modifica los datos de un empleado. |
| **Eliminar** | Elimina un empleado por su ID.  |

---

## **Consideraciones Finales**

- Se debe tener una base de datos MySQL en ejecución y configurada adecuadamente.
- Es posible modificar la clase `Empleado` para añadir más atributos o personalizar la estructura de la tabla.
- Se deben capturar y manejar adecuadamente las excepciones que puedan surgir durante las operaciones CRUD.