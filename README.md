# PC-CHECK

Es un Software multiplataforma Cliente-Servidor desarrollado en Java, especialmente para vigilar los componentes basicos de Hardware (CPU, RAM, GPU) desde otro PC o dispositivo electronico similar (NUC, Raspberry Pi). Fue creado especialmente para el modding, indicando mediante una pantalla instalada en la PC mostrando el estado del hardware de la maquina objetivo, y enviando esta informacion a un dispositivo ajeno al pc servidor.

## Contenido
* [Caracteristicas](https://github.com/Garnica1999/PCCheck#caracteristicas)
* [Pre-requisitos](https://github.com/Garnica1999/PCCheck#pre-requisitos-)
* [Comenzando](https://github.com/Garnica1999/PCCheck#comenzando)
* [Instalacion](https://github.com/Garnica1999/PCCheck#instalaci%C3%B3n-)
* [Ejecucion](https://github.com/Garnica1999/PCCheck#ejecuci%C3%B3n-%EF%B8%8F)
* [Construido con](https://github.com/Garnica1999/PCCheck#construido-con-%EF%B8%8F)
* [Contribuyendo](https://github.com/Garnica1999/PCCheck#contribuyendo-%EF%B8%8F)
* [Wiki](https://github.com/Garnica1999/PCCheck#wiki-)
* [Versionado](https://github.com/Garnica1999/PCCheck#versionado-)
* [Autores](https://github.com/Garnica1999/PCCheck#autores-%EF%B8%8F)
* [Licencia](https://github.com/Garnica1999/PCCheck#licencia-)
* [Expresiones de Gratitud](https://github.com/Garnica1999/PCCheck#expresiones-de-gratitud-)


## Caracteristicas
* Software ejecutable en sistemas operativos con escritorio o sin el (Para servidores)
* Bajo consumo de los recursos del PC servidor y cliente al ejecutarse el software
* Proporciona informacion completa del procesador y de la ram
* Proporciona informacion de las tarjetas graficas instaladas en el PC servidor (Proximamente)
* Precision al dar informacion relacionada con temperaturas, uso e informacion del harware basico del PC
* Diseñado mod de PCs con pantalla en el gabinete
* Disedo para Raspberry Pi con pantallas de gabinetes coenctadas a este(Falta probar)

## Pre-requisitos 📋

* **CPU:** Pentium IV o superior
* **RAM:** 512 MB o superior
* **RED:** Conexion cableada o inalambrica estable. Ambos equipos (PC Servidor y PC Cliente) deben de estar en la misma red
* **GPU:** La GPU es opcional
* **Sistema Operativo:** Windows 7 o superior, Linux, MacOS

## Comenzando

Si quieres probar **PC-CHECK** es muy facil, primero debes descargar el programa PC-CHECK, tanto para el servidor como para el cliente, los links los puedes encontrar a contonuacion:
* [PC-CHECK Servidor](https://github.com/Garnica1999/PCCheck/blob/master/PC-CheckServer/target/PC-CheckServer-1.0-SNAPSHOT.jar) - Porgrama final para el servidor
* [PC-CHECK Cliente](https://github.com/Garnica1999/PCCheck/blob/master/PC-CheckClient/dist/PC-CheckClient.jar) - Software final para el PC cliente

Para continuar con la instalacion de PC-CHECK, sigue el apartado Instalacion haciendo clic [aqui](https://github.com/Garnica1999/PCCheck#instalaci%C3%B3n-)

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

Para que el software, tanto en el PC cliente como en el PC servidor funcione correctamente, en la carpeta lib de este repositorio, se encuentran las librerias DLL (Windows) y so (Linux). 
* [Windows](https://github.com/Garnica1999/PCCheck/tree/master/lib/windows) - Librerias para Windows
* [Linux](https://github.com/Garnica1999/PCCheck/tree/master/lib/linux) - Librerias para Linux

Estas deben de ser incluidas en el `java.library.path` (Ruta de instalacion en donde instalaste java). Recuerda pegarlas en la carpeta bin dentro de esta ruta. No te saltes este paso, ya que el software no funcionara. 

Para Windows, debes de copiar todas las librerias **.dll** en la carpeta de instalacion de java y en la ruta `C:\Windows\system32`

### Para desarrolladores

Si tu solo eres desarrollador, y quieres contribuir con el codigo de PC-CHECK, clona este repositorio mediante GitHub Desktop, o simplemente mediante el siguiente comando, para descargarlo en tu PC y poder trabajr comodamente:

```
git clone git://github.com/Garnica1999/PCCheck.git PC-CHECK
```
Posteriormente, puedes editar este proyecto utilizando el entorno de desarrollo para Java [NetBeans](https://netbeans.org/), recomendamos la version 8.2 o superior. Con este IDE podras manipular el codigo tanto del proyecto para PC Servidor como para PC Cliente.

## Ejecución ⚙️

Para ejecutar PC-CHECK solo ejecuta el siguiente comando.
### Para PC Servidor

Ejecutar el siguiente comando en modo administrador:
```
java -jar PC-CheckServer.jar
```
Al ejecutarlo te mostrara el siguiente mensaje:
```
Welcome to PC-CHECK
Establishing connection with the PC-CHECK client
```
Es aqui en donde debes ejecutar en el cliente PC-CHECK con su correspondiente binario, el tutorial lo puedes mirar haciendo clic [aqui](https://github.com/Garnica1999/PCCheck#para-pc-cliente)

Un ejemplo de la salida del servidor podria ser el siguiente:
```
Welcome to PC-CHECK
Establishing connection with the PC-CHECK client
Successfully established connection
Starting... It may take several seconds
```
En futuras versiones mejoraremos las impresiones y el Log para el servidor.

### Para PC Cliente

Para ejecutar el cliente, primero necesitas la ip del servidor. Esta la puedes obtener ingresando los siguientes comando en el terminal/shell/cmd del pc servidor:
* **Linux:** ifconfig
* **Windows:** ipconfig

El comando de ejecucion es el siguiente:
```
java -jar PC-CheckClient.jar <ServerIP> -console
```
Los argumentos se describen a continuacion:
* **ServerIP:** Es la IP del servidor el cual debe de estar ejecutandose la version PC-CHECK en este
* **-console** Prepara el programa para ejecutarse en modo consola. Por el momento este comando es obligatorio

Un ejemplo del comando para ejecutar el cliente es el siguiente:

```
java -jar PC-CheckClient.jar 192.168.0.19 -console
```
Al ejecutarlo empezaras a ver informacion tanto del CPU como de la RAM, esa informacion es del PC Servidor, y esto significa que el software esta funcionando correctamente. Un ejemplo de la salida del programa puede ser:
```
CPU:
__________________________________
Processor : GenuineIntel-Intel(R) Core(TM) i5-7400 CPU @ 3.00GHz
Frecuency: 3000000000 Hz
Use: 23.765902190773236%
Physic Cores: 4
Logic Cores: 4
Package Temperature: 33.0 C
Core Information:
        Core Id: 0
        Use: 27.293729372937293%
        Temperature Core 0: 32.0 C
-----------------------------------
        Core Id: 1
        Use: 25.26385224274406%
        Temperature Core 1: 33.0 C
-----------------------------------
        Core Id: 2
        Use: 19.591029023746703%
        Temperature Core 2: 31.0 C
-----------------------------------
        Core Id: 3
        Use: 24.21643022104916%
        Temperature Core 3: 31.0 C
-----------------------------------

__________________________________
RAM:
Total: 17000132608MB
Used: 7176564736KB
Free: 9823567872KB
Porcent Used: 42.21475738737956%
Porcent Free: 57.78524261262044%
```

## Construido con 🛠️

* [Java](https://www.java.com/es/) - Lenguaje de Programacion
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [NetBeans](https://netbeans.org/) - Entorno de Desarrollo
* [Sigar API](https://github.com/hyperic/sigar) - API para obtener informacion del sistema
* [JSensor](https://github.com/profesorfalken/jSensors) - Monitorizacion de todos los sensores de hardware de una PC
* [OSHI](https://github.com/oshi/oshi) - Native Operating System and Hardware Information
* [JNA](https://github.com/java-native-access/jna) - Java Native Access

## Contribuyendo 🖇️

Por favor lee el CONTRIBUTING.md para detalles de nuestro código de conducta, y el proceso para enviarnos pull requests.

## Wiki 📖

Puedes encontrar mucho más de cómo utilizar este proyecto en nuestra [Wiki](https://github.com/Garnica1999/PCCheck/wiki)

## Versionado 📌

Usamos [SemVer](http://semver.org/) para el versionado. Para todas las versiones disponibles, mira los [tags en este repositorio](https://github.com/Garnica1999/PCCheck/tags).

## Autores ✒️

* **Carlos Garnica** - *Trabajo Inicial* - [Garnica1999](https://github.com/garnica1999)

También puedes mirar la lista de todos los [contribuyentes](https://github.com/Garnica1999/PCCheck/contributors) quíenes han participado en este proyecto. 

## Licencia 📄

Este proyecto está bajo la Licencia MIT - mira el archivo [LICENSE.md](LICENSE.md) para detalles

## Expresiones de Gratitud 🎁

* proximamente...

