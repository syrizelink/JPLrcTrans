# LyricsRomajiConversion

> ❗注意：本项目可能大量存在不规范的代码，有Bug反馈或者是优化建议欢迎发issue告诉我.



### 一个用于将日文歌词转换为罗马字 / 谐音形式歌词的简单工具


------
#### 前言：

&emsp;&emsp;目前逝大一，Java学习阶段练手写的一个小工具，写的很烂．

&emsp;&emsp;通常而言在音乐平台下载下来的歌词文件(.lrc)即使平台给了音译功能也下不了罗马音形式的歌词，就更不要说谐音了，可以看到有些博主会出一些歌曲进行谐音标注后的视频，可显而易见的不可能涵盖很多曲目，于是就有了这个项目．如果你喜欢唱日语歌又苦于不会读，那么这个小东西应该很适合你．

&emsp;&emsp;运行时会自动将歌词中的真名转换成假名，所以不需要标注假名即可进行自动转换；在转换时会自动地跳过非日文字符以尽量避免出错．

&emsp;&emsp;采用罗马字标准为平文式罗马字（亦称＂黑本式罗马字＂），关于该标注方法可参见 [ヘボン式ローマ字綴方表 (mofa.go.jp)](https://www.ezairyu.mofa.go.jp/passport/hebon.html)；谐音部分则由个人进行标注，由于我并不是日语专业的学生也没有系统的学过日语，因此可能存在谬误，如果你发现了错误的地方请务必指出．

&emsp;&emsp;为了尽可能的易读，在标注谐音的时候尽量保证读音相近的前提之下选择了常用字，而一些假名我觉得保留罗马音可能更易读，比如"き"(ki)就没有标注．


------
#### 已知问题：

- 在歌词中含有特殊字符如（: . / -）时可能出现错误匹配歌词行或匹配冗余行
- 在自动转换假名的过程中, 由于歌词中没有注音假名且无发音信息, 因此得到的罗马音 / 谐音会和读音有所差别, 这是自动转换时不可避免的错误, 譬如君(きみ)在自动转换时会转为平假名"クン"而非标注值


------
#### 如何使用

Java16+( 必要依赖 )

前往Oracle下载安装包：[Java Archive Downloads - Java SE 16 (oracle.com)](https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html)

选择对应版本下载安装后配置好环境变量即可

之后安装提示一步步操作即可完成转换


------
#### 依赖库

1. Kuromoji
2. Gson
3. 没了


------
#### 测试

已在Windows 10/11 平台测试通过


------
#### Bug / 需求

- 如果你再使用过程中发现任何的问题, 欢迎你提交**Issue**来告知我以便及时解决你的问题以及进行Bug修复

- 有好的想法或是想让我添加新的功能也欢迎告诉我, 你只需要在提交**Issue**时加上**enhancement**标签即可

- 当然, 如果你有能力也欢迎提交PR来做出改进


------
| ✅TODO(画饼)：       |
|------------------|
| ⬜  多文件处理         |
| ⬜  双行歌词支持        |
| ⬜  精准歌词行匹配规则     |
| ⬜  易用的图形化界面 (可能) |
