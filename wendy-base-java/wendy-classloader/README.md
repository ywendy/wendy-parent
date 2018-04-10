
# classloader

## classloader 加载
![image](https://raw.githubusercontent.com/ywendy/wendy-parent/master/wendy-base-java/wendy-classloader/src/main/resources/img/java-classloader001.png)


## 自定义classloader无法加载java.lang 下的类 
jvm 限制，即使打破双亲委派原则，由自定义类加载器加载也无法做到.


