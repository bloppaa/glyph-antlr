# Glyph
---

## Introducción


Glyph es un lenguaje de programación basado en emojis, donde las palabras clave y los tokens tradicionales son reemplazados por símbolos visuales. Diseñado para ser intuitivo y llamativo, Glyph combina una estética lúdica con una funcionalidad sólida, permitiendo manejar conceptos esenciales de programación como variables, control de flujo y funciones.

## Gramática

### Tipos

Existen cuatro tipos primitivos. A continuación se especifican los valores que pueden tomar cada uno de estos tipos:

- `int (🧮)`: números enteros que cumplan con la expresión regular `'0' | [1-9][0-9]*`. Ejemplos de valores válidos son `0`, `123`, `-333`, etc.
- `float (🛟)`: números de punto flotante, que cumplan con la expresión regular `('0' | [1-9][0-9]*) ('.' [0-9]+)?`. Ejemplos de valores válidos son `3.14`, `0.001`, `1`, etc. Fijarse que es posible asignar enteros a este tipo, no así viceversa.
- `bool (✅)`: valores binarios, que sólo pueden tomar el valor `👍` *(expresión verdadera)* o `👎` *(expresión falsa)*.
- `string (📄)`: cualquier combinación de caracteres delimitadas por el token `🧵`. Ejemplos de valores válidos son `🧵🧵`, `🧵Hola Mundo🧵`, `🧵123🧵`, etc.

### Variables

Las variables se declaran de la siguiente manera:

`tipo-variable id-variable 👉 valor-variable ✋`, donde:


- `tipo-variable` puede ser uno de los cuatros tipos primitivos: *int* (🧮), *float* (🛟), *bool* (✅) o *string* (📄).
- `id-variable` es cualquier string que cumpla con la expresión regular `[a-z][a-zA-Z0-9_]*`.
- `👉` es el operador de asignación.
- `valor-variable` es el valor acorde al tipo de dato.
- `✋` es el token de fin de sentencia.

Ejemplos de declaraciones son:

```
🧮 sum 👉 0 ✋
🛟 pi 👉 3.14 ✋
✅ isPrime 👉 👎 ✋
📄 greeting 👉 🧵Hola Mundo🧵 ✋
```

Por supuesto, también se pueden reasignar las variables de la siguiente manera:

`id-variable 👉 nuevo-valor ✋`. Por ejemplo:

```
🧮 sum 👉 0 ✋
sum 👉 1 ✋
```

Cabe destacar que no es posible reasignar un valor con un tipo distinto al declarado. El siguiente ejemplo arrojará un error de tipos incompatibles:

```
🧮 sum 👉 0 ✋
sum 👉 1.0 ✋ 💭 No es posible asignar un float a int
```

> *Nota:* El emoji 💭 es usado para escribir comentarios. Cualquier caracter escrito hasta un salto de línea será ignorado por el compilador.

### Operadores

Las operaciones permitidas son:

- Aritméticas: *suma* (➕), *resta* (➖), *multiplicación* (✖️), *división (➗)* y *módulo* (🪙).

```
1 ➕ 2 ✋ 💭 3
3 ➖ 2 ✋ 💭 1
2 ✖️ 3 ✋ 💭 6
6 ➗ 2 ✋ 💭 3
5 🪙 2 ✋ 💭 1
```

- Lógicas: *and* (🤝), *or* (🤷) y *not* (🙅).

```
👍 🤝 👎 ✋ 💭 👎
👍 🤷 👎 ✋ 💭 👍
🙅 👎 ✋ 💭 👍
```

- Relacionales: *igual* (🟰), *distinto* (🚫), *mayor* (▶️), *menor* (◀️), *mayor o igual* (⏩) y *menor o igual* (⏪).

```
1 🟰 1 ✋ 💭 👍
1 🚫 1 ✋ 💭 👎
1 ▶️ 1 ✋ 💭 👎
0 ◀️ 1 ✋ 💭 👍
1 ⏩ 1 ✋ 💭 👍
0 ⏪ 1 ✋ 💭 👍
```

El resultado de las operaciones puede ser asignado a una variable:

```
🧮 sum 👉 1 ➕ 2 ✋
✅ areEqual 👉 1 🟰 1 ✋
```

### Control de flujo

#### If-else

Un bloque *if-else* se define de la siguiente manera:

```
🤔 🧎‍➡️expresión-booleana🧎
🏃‍➡️
    instrucción-if-1 ✋
    instrucción-if-2 ✋
    ...
🏃 👇 🏃‍➡️
    instrucción-else-1 ✋
    instrucción-else-2 ✋
    ...
🏃
```
Donde:

- `🤔` es la keyword que indica el inicio de un bloque *if*.
- `🧎‍➡️` es el token de apertura de la expresión booleana.
- `expresión-booleana` es cualquier expresión que devuelva un valor booleano.
- `🧎` es el token de cierre de la expresión booleana.
- `🏃‍➡️` es el token de apertura del bloque de instrucciones.
- `🏃` es el token de cierre del bloque de instrucciones.
- `instrucción-if-i` son las instrucciones que se ejecutan si la expresión es verdadera.
- `👇` es la keyword *opcional* que indica el inicio de un bloque *else*.
- `instrucción-else-i` son las instrucciones que se ejecutan si la expresión es falsa.

Ejemplo:

```
🧮 num 👉 3 ✋
🤔 🧎‍➡️num 🪙 2 🟰 0🧎
🏃‍➡️
    🖨️🧎‍➡️🧵Es par🧵🧎✋
🏃 👇 🏃‍➡️
    🖨️🧎‍➡️🧵Es impar🧵🧎✋
🏃
```
> *Nota:* La keyword `🖨️` permite mostrar expresiones por consola.

#### While-loop

Un bloque *while* se define de la siguiente manera:

```
🌀 🧎‍➡️expresión-booleana🧎
🏃‍➡️
    instrucción-1 ✋
    instrucción-2 ✋
    ...
🏃
```
Donde:

- `🌀` es la keyword que indica el inicio de un bloque *while*.

Ejemplo:

```
🧮 num 👉 0 ✋
🌀 🧎‍➡️num 🚫 10🧎
🏃‍➡️
    🖨️🧎‍➡️num🧎✋
    num 👉 num ➕ 1 ✋
🏃
```

#### For-loop

Un bloque *for* se define de la siguiente manera:

```
🔁 🧎‍➡️variable 👉 inicio ➡️ fin 👟 paso🧎
🏃‍➡️
    instrucción-1 ✋
    instrucción-2 ✋
    ...
🏃
```
Donde:

- `🔁` es la keyword que indica el inicio de un bloque *for*.
- `variable` es el identificador de la variable que se utilizará en el ciclo.
- `inicio` es el valor inicial de la variable.
- `➡️` simplemente indica que se está definiendo el rango de la variable.
- `fin` es el valor final de la variable.
- `👟` es un token *opcional* que indica la cantidad de pasos del ciclo.
- `paso` es la cantidad de pasos que se incrementará la variable en cada iteración.

Ejemplo:
```
🔁 🧎‍➡️i 👉 0 ➡️ 10 👟 2🧎
🏃‍➡️
    🖨️🧎‍➡️i🧎✋
🏃
```

### Funciones

Las funciones se definen de la siguiente manera:

```
🍿 nombre-función 🧎‍➡️tipo-1 var-1🔸 tipo-2 var-2🧎
🏃‍➡️
    instrucción-1 ✋
    instrucción-2 ✋
    ...
    ↩️ expresión ✋
🏃
```
Donde:

- `🍿` es la keyword que indica el inicio de una función.
- `nombre-función` es el identificador de la función.
- `tipo-i` es el tipo de dato del parámetro *opcional*.
- `var-i` es el identificador del parámetro *opcional*.
- `🔸` es el token que separa los parámetros.
- `↩️` es la keyword que indica el retorno de la función.

Ejemplo:
```
🍿 isEven 🧎‍➡️🧮 num🧎
🏃‍➡️
    ↩️ num 🪙 2 🟰 0 ✋
🏃
```

Una vez definidas las funciones, se pueden llamar de la siguiente manera:

`nombre-función🧎‍➡️var-1🔸 var-2🧎✋`.

## Compilador

El compilador de Glyph fue implementado utilizando ANTLR4. ANTLR4 es una herramienta que permite generar parsers a partir de gramáticas definidas en un archivo de texto.

La ventaja de usar ANTLR4 es que permite definir tanto los tokens como la gramática en un sólo archivo, generando un parser y un lexer en el lenguaje de programación deseado. En este caso, se utilizó Java para implementar el compilador.

### Tokens