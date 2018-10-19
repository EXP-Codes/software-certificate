# software-certificate

> <b>软件授权清单&nbsp;:&nbsp;</b>[[ Management-Page ]](https://lyy289065406.github.io/certificate/)

------

## 运行环境

　![](https://img.shields.io/badge/Platform-Windows%20x64-brightgreen.svg)  ![](https://img.shields.io/badge/Platform-Unix%20x64-brightgreen.svg)  ![](https://img.shields.io/badge/JDK-1.7%2B-brightgreen.svg)

## 插件介绍

　此插件是供开发者使用的，通过它可以利用Github服务器发布若干个应用的授权信息。

　然后这些应用可以通过Github服务器提取自身的授权信息完成自检。

　目前提供的自检方案主要有：

- 有效时间校验
- 有效版本校验
- 黑名单校验
- 白名单校验


## 实现原理

![实现原理](https://raw.githubusercontent.com/lyy289065406/certificate/master/doc/01-%E8%BD%AF%E4%BB%B6%E6%8E%88%E6%9D%83%E6%A0%A1%E9%AA%8C%E5%8E%9F%E7%90%86.png)


　在Github的每个Repository都是可以发布一个静态页面的，而且这个静态页面可以在公网访问。

　这个静态页面初衷是用来介绍Repository的，但是也可以利用它作为一个静态服务页，实现其他功能。


> **为某个Repository发布静态页面的方法：**
<br/>　　（1） 打开这个Repository的仓库首页
<br/>　　（2） 进入 `Settings` 页面
<br/>　　（3） 找到 `GitHub Pages`，在 `Source`下面有一个下拉框， 默认是 `None`， 修改为 `master branch`
<br/>　　（4） 然后刷新 `Settings` 页面，在 `GitHub Pages` 的位置会提示：Your site is published at `xxx`
<br/>　　（5） 在Repository根目录新建一个 `index.html` 文件，写入页面内容，就可以通过这个发布地址访问它了


　回到这个插件，其实原理就很简单了，要实现的功能主要有两个：

- 通过更新index.html，在静态页面维护被授权应用的信息
- 应用从静态页面提取自己的授权信息进行自检


## 使用方式

。。。


## 版权声明

　[![Copyright (C) 2016-2018 By EXP](https://img.shields.io/badge/Copyright%20(C)-2006~2018%20By%20EXP-blue.svg)](http://exp-blog.com)　[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

- Site: [http://exp-blog.com](http://exp-blog.com) 
- Mail: <a href="mailto:289065406@qq.com?subject=[EXP's Github]%20Your%20Question%20（请写下您的疑问）&amp;body=What%20can%20I%20help%20you?%20（需要我提供什么帮助吗？）">289065406@qq.com</a>


------
