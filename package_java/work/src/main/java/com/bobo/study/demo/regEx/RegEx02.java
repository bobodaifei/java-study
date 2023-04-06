package com.bobo.study.demo.regEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx02 {
  public static void main(String[] args) {
    String content = "2010流行原因流行语能病毒式传播离127.0.0.1不开体裁简洁和短视频的发展。在快节奏生活下，简洁的形式更容易被受众理解并利用。“奥利给”一词只有简单的三个字，一旦理解了其中的“梗”，便很易于传播。此外，快文化背景下的主要产物之一就是短视频，网络短视频形成了小型的互动社区，大量原创用户积极投入拍摄，创造了一个内容丰富有趣的媒介生态环境。在B站，名为“正能量语录奥利给”的18秒视频，播放量就达到了2683万，弹幕数量也达到了10.1万。“奥利给”在短视频中的传播，因有大量互动弹幕而提高了其关键词的到达率，弹幕上的“奥利给”也容易吸引观看者的注意，使观看者更有兴趣探索其含义和效仿利用这一流行语。网友有求新从众心理。“奥利给”的原身“给力”走红于2010年，在年轻人心中已经过时，网友需要更适合当下的词汇，“奥利给”便是一个很好的替代品。它更年轻有趣，满足网友追求新鲜事物的心理。同时，当网络流行语流行到一定程度时，从众心理会使人们为了追赶潮流而学习使用。“奥利给”正能量的表达方式。“奥利给”是具备正能量的流行语，各新闻媒体包容它的存在，并利用它拉近与网络人群的距离感。自媒体个人频繁使用它，不仅增强个人的人格魅力，也吸引观众关注。“冬泳怪鸽”喊出“加油，奥利给！”的时候，也是在向受众传播正能量。积极正面的流行语引导正面情绪,引领受众传播正面情感,产生积极的社会影响，可获得更多认可。 [2] 发展过程2019年，冬泳怪鸽的“加油，奥利给”以及岛市老八的“奥利给，干了兄弟们”进行创作的关于奥利给的二次创作开始流行。而后“岛市老八”在快手平台复出，继续吃播视频创作，并成为B站鬼畜中的素材，干呕声被制成音MAD。同时，岛市老八的行为赋予了“奥利给”一词新的意思，即固态排泄物。 [2]  [4] 2019年10月，“奥利给”已被广泛传播。在2020年1月1日0时50分左右央视新闻频道的《年轮2019》中，有“加油，奥利给”的语音片段。 [2] ";

    // 1. \\d 表示一个任意的数字
    // 2. 创建模式对象[即正则表达式对象]

    // Pattern pattern1 = Pattern.compile("(\\d\\d)(\\d\\d)");
    Pattern pattern = Pattern.compile("\\d\\d\\d\\d");
    // 创建一个匹配器对象，matcher按照pattern样式，到content文本中进行匹配
    // 找到就返回true，否则返回false
    Matcher matcher = pattern.matcher(content);
    // 循环匹配
    /**
     *
     * matcher.find() 完成的任务 （考虑分组）
     * 什么是分组，比如 (\d\d)(\d\d) ,正则表达式中有() 表示分组,第1个()表示第1组,第2个()表示第2组...
     * 
     * 1. 根据指定的规则 ,定位满足规则的子字符串(比如(2010))
     * 2. 找到后，将 子字符串的开始的索引记录到 matcher对象的属性 int[] groups;
     * 2.1 groups[0] = 0 , 把该子字符串的结束的索引+1的值记录到 groups[1] = 4
     * 
     * 2.2 记录1组()匹配到的字符串 groups[2] = 0 groups[3] = 2
     * 2.3 记录2组()匹配到的字符串 groups[4] = 2 groups[5] = 4
     * 2.4.如果有更多的分组.....
     * 
     * 3. 同时记录oldLast 的值为 子字符串的结束的 索引+1的值即4, 即下次执行find时，就从4开始匹配
     *
     * matcher.group(0) 分析
     *
     * 源码:
     * public String group(int group) {
     * if (first < 0)
     * throw new IllegalStateException("No match found");
     * if (group < 0 || group > groupCount())
     * throw new IndexOutOfBoundsException("No group " + group);
     * if ((groups[group*2] == -1) || (groups[group*2+1] == -1))
     * return null;
     * return getSubSequence(groups[group * 2], groups[group * 2 + 1]).toString();
     * }
     * 1. 根据 groups[0]=0 和 groups[1]=4 的记录的位置，从content开始截取子字符串返回
     * 就是 [0,4) 包含 0 但是不包含索引为 4的位置
     *
     * 如果再次指向 find方法.仍然安上面分析来执行
     */

    while (matcher.find()) {
      // 小结
      // 1. 如果正则表达式有() 即分组
      // 2. 取出匹配的字符串规则如下
      // 3. group(0) 表示匹配到的子字符串
      // 4. group(1) 表示匹配到的子字符串的第一组字串
      // 5. group(2) 表示匹配到的子字符串的第2组字串
      // 6. ... 但是分组的数不能越界.
      System.out.println(matcher.group(0));
      // System.out.println(matcher.group(1));
    }
  }
}
