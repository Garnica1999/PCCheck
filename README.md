# PC-CHECK

Es un Software multiplataforma Cliente-Servidor desarrollado en Java, especialmente para vigilar los componentes basicos de Hardware (CPU, RAM, GPU) desde otro PC o dispositivo electronico similar (NUC, Raspberry Pi). Fue creado especialmente para el modding, indicando mediante una pantalla instalada en la PC mostrando el estado del hardware de la maquina objetivo, y enviando esta informacion a un dispositivo ajeno al pc servidor.

## Pre-requisitos 📋

* **CPU:** Pentium IV o superior
* **RAM:** 512 MB o superior
* **RED:** Conexion cableada o inalambrica estable. Ambos equipos (PC Servidor y PC Cliente) deben de estar en la misma red
* **GPU:** La GPU es opcional
* **Sistema Operativo:** Windows 7 o superior, Linux, MacOS

## Instalación 🔧

### Instalacion de Java: 

Para que PC-CHECK funcione correctamente, primero debe instalar Java en su version 8 o superior, tanto en el PC Servidor como en el PC Cliente. Esta instalacion varia del sistema operativo (En el caso de Linux varia la instalacion dependiendo de la distribucion).
Primero, en todas las plataformas puedes mirar si tienes instalado Java con el siguiente comando:

```
java -version
```
Debe de dar una salida similar a esta:

```
Output:
openjdk version "10.0.1" 2018-04-17
OpenJDK Runtime Environment (build 10.0.1+10-Ubuntu-3ubuntu1)
OpenJDK 64-Bit Server VM (build 10.0.1+10-Ubuntu-3ubuntu1, mixed mode)
```
En caso de **no dar esta salida**, te recomendamos seguir con la instalacion de Java en las maquinas correspondientes. Las instrucciones para Windows y Linux estan a continuacion:

**Windows** 

Para instalarlo en Windows, recomendamos el siguiente link (Requiere cuenta de Oracle para descargar el instalador de Oracle):
https://www.java.com/es/download/

**Linux:**
* Ubuntu: Para instalar Java en Ubuntu, copia y pega los siguientes comendos

```
sudo apt-get update
sudo apt-get upgrade
sudo apt install default-jre
```
* Fedora: Para instalar Java en Fedora, sigue los siguientes pasos:
1. Ejecuta el siguiente comando para obtener uns lista con las versiones disponibles:

```
dnf search openjdk
```
2. Copia la version de OpenJDK que quieres instalar. Recomendamos la version igual o superior a 8.

3. Ejecuta el siguiente comando para instalar OpenJDK:
```
dnf install <openjdk-package-name>
```
Por ejemplo:
```
dnf install java-1.8.0-openjdk.x86_64
```

* Raspbian (Raspberry Pi)

```
sudo apt-get install oracle-java8-jdk
```
### Librerias

Para que el software, tanto en el PC cliente como en el PC servidor funcione correctamente, en la carpeta lib de este repositorio, se encuentran las librerias DLL (Windows) y so (Linux). Estas deben de ser incluidas en el `java.library.path` (Ruta de instalacion en donde instalaste java). Recuerda pegarlas en la carpeta bin dentro de esta ruta. No te saltes este paso, ya que el software no funcionara.

### Para desarrolladores

Si tu solo eres desarrollador, y quieres contribuir con el codigo de PC-CHECK, clona este repositorio mediante GitHub Desktop, o simplemente mediante el siguiente comando, para descargarlo en tu PC y poder trabajr comodamente:

```
git clone git://github.com/Garnica1999/PCCheck.git PC-CHECK
```
Posteriormente, puedes editar este proyecto utilizando el entorno de desarrollo para Java [NetBeans](https://netbeans.org/), recomendamos la version 8.2 o superior. Con este IDE podras manipular el codigo tanto del proyecto para PC Servidor como para PC Cliente.

## Ejecución ⚙️

Para ejecutar PC-CHECK solo ejecuta el siguiente comando.
### Para PC Servidor
```
java -jar PC-CheckServer.jar
```
Al ejecutarlo posiblemente no te salga nada. Tranquilo, es normal, ya que el servidor esta esperando a que el cliente se conecte. Para ello ve a la PC donde quieras que se vea la informacion del PC Servidor, instala Java y prepara ese equipo (Lee los puntos anteriores), y posteriormente, funcionara.

Esto se vera reflejado con una impresion de algunos numeros, es normal, esto quiere decir que el pc servidor logro conectarse con el pc cliente correctamente, y esta enviando informacion del hardware al pc cliente satisfactoriamente.

Un ejemplo de la salida del servidor podria ser el siguiente:
```
______________________________
16216
9721516032
7278616576
______________________________
16216
9721483264
7278649344
______________________________
```
### Para PC Cliente
```
java -jar PC-CheckClient.jar
```
Al ejecutarlo empezaras a ver informacion tanto del CPU como de la RAM, esa informacion es del PC Servidor, y esto significa que el software esta funcionando correctamente. Un ejemplo de la salida del programa puede ser:
```
CPU{model=Core(TM) i5-7400 CPU @ 3.00GHz, vendor=Intel, cores=4, totalCores=4, frecuency=3000, use=10.824230387288978}
RAM{total=16216, used=7260880896, free=9739251712, porcentFree=57.289269069682774, porcentUsed=42.710730930317226}
CPU{model=Core(TM) i5-7400 CPU @ 3.00GHz, vendor=Intel, cores=4, totalCores=4, frecuency=3000, use=17.81637717121588}
RAM{total=16216, used=7262687232, free=9737445376, porcentFree=57.278643646683726, porcentUsed=42.721356353316274}
CPU{model=Core(TM) i5-7400 CPU @ 3.00GHz, vendor=Intel, cores=4, totalCores=4, frecuency=3000, use=12.581128307538691}
RAM{total=16216, used=7262666752, free=9737465856, porcentFree=57.27876411633224, porcentUsed=42.72123588366776}
```

## Construido con 🛠️

* [Java](https://www.java.com/es/) - Lenguaje de Programacion
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [NetBeans](https://netbeans.org/) - Entorno de Desarrollo
* [Sigar API](https://github.com/hyperic/sigar) - API para obtener informacion del sistema
* [JSensor](https://github.com/profesorfalken/jSensors) - Monitorizacion de todos los sensores de hardware de una PC

## Contribuyendo 🖇️

Por favor lee el CONTRIBUTING.md para detalles de nuestro código de conducta, y el proceso para enviarnos pull requests.

## Wiki 📖

Puedes encontrar mucho más de cómo utilizar este proyecto en nuestra [Wiki](https://github.com/tu/proyecto/wiki)

## Versionado 📌

Usamos [SemVer](http://semver.org/) para el versionado. Para todas las versiones disponibles, mira los [tags en este repositorio](https://github.com/tu/proyecto/tags).

## Autores ✒️

* **Carlos Garnica** - *Trabajo Inicial* - [Garnica1999](https://github.com/garnica1999)

También puedes mirar la lista de todos los [contribuyentes](https://github.com/your/project/contributors) quíenes han participado en este proyecto. 

## Licencia 📄

Este proyecto está bajo la Licencia MIT - mira el archivo [LICENSE.md](LICENSE.md) para detalles

## Expresiones de Gratitud 🎁

* Comenta a otros sobre este proyecto 📢
* Invita una cerveza 🍺 a alguien del equipo. 
* Da las gracias públicamente 🤓.
* etc.

