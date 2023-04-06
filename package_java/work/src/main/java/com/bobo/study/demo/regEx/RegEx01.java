package com.bobo.study.demo.regEx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx01 {
  static String content;
  static {

    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:\\桌面\\节点.txt")),"UTF-8"));
      // byte[] bytes = new byte[10240];
      // int count;
      String str = "";
      StringBuffer stringBuffer = new StringBuffer();
      while ((str = reader.readLine()) != null) {
        stringBuffer.append(str);
      }
      content = stringBuffer.toString();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    String content = "流行原因流行语能病毒式传播离不开体裁简洁和短视频的发展。在快节奏生活下，简洁的形式更容易被受众理解并利用。“奥利给”一词只有简单的三个字，一旦理解了其中的“梗”，便很易于传播。此外，快文化背景下的主要产物之一就是短视频，网络短视频形成了小型的互动社区，大量原创用户积极投入拍摄，创造了一个内容丰富有趣的媒介生态环境。在B站，名为“正能量语录奥利给”的18秒视频，播放量就达到了2683万，弹幕数量也达到了10.1万。“奥利给”在短视频中的传播，因有大量互动弹幕而提高了其关键词的到达率，弹幕上的“奥利给”也容易吸引观看者的注意，使观看者更有兴趣探索其含义和效仿利用这一流行语。网友有求新从众心理。“奥利给”的原身“给力”走红于2010年，在年轻人心中已经过时，网友需要更适合当下的词汇，“奥利给”便是一个很好的替代品。它更年轻有趣，满足网友追求新鲜事物的心理。同时，当网络流行语流行到一定程度时，从众心理会使人们为了追赶潮流而学习使用。“奥利给”正能量的表达方式。“奥利给”是具备正能量的流行语，各新闻媒体包容它的存在，并利用它拉近与网络人群的距离感。自媒体个人频繁使用它，不仅增强个人的人格魅力，也吸引观众关注。“冬泳怪鸽”喊出“加油，奥利给！”的时候，也是在向受众传播正能量。积极正面的流行语引导正面情绪,引领受众传播正面情感,产生积极的社会影响，可获得更多认可。 [2] 发展过程2019年，冬泳怪鸽的“加油，奥利给”以及岛市老八的“奥利给，干了兄弟们”进行创作的关于奥利给的二次创作开始流行。而后“岛市老八”在快手平台复出，继续吃播视频创作，并成为B站鬼畜中的素材，干呕声被制成音MAD。同时，岛市老八的行为赋予了“奥利给”一词新的意思，即固态排泄物。 [2]  [4] 2019年10月，“奥利给”已被广泛传播。在2020年1月1日0时50分左右央视新闻频道的《年轮2019》中，有“加油，奥利给”的语音片段。 [2] ";

    // 创建一个pattern对象，模式对象，可以理解成就是一个正则表达式
    // Pattern pattern = Pattern.compile("[0-9]+");
    // Pattern pattern = Pattern.compile("[a-zA-Z]+");
    Pattern pattern = Pattern.compile("([a-zA-Z]+)|([0-9]+)");
    // 创建一个匹配器对象，matcher按照pattern样式，到content文本中进行匹配
    // 找到就返回true，否则返回false
    Matcher matcher = pattern.matcher(content);
    // 循环匹配
    while (matcher.find()) {
      System.out.println(matcher.group(0));
    }

  }
}

class Test01 {
  public static void main(String[] args) {
    Pattern pattern = Pattern.compile("<a target=\"_blank\" title=\"(\\S*)\"");
    // 创建一个匹配器对象，matcher按照pattern样式，到content文本中进行匹配
    // 找到就返回true，否则返回false
    Matcher matcher = pattern.matcher(RegEx01.content);
    // 循环匹配
    while (matcher.find()) {
      System.out.println(matcher.group(1));
    }
  }
}
