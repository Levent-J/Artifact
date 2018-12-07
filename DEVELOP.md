# 开发文档
这里是开发文档，包含了具体的开发计划、任务清单、V社的Api文档等。

## 开发计划
现阶段先梳理一下api文档，之后定一个大概的流程图和界面的样式，之后定一下任务清单，就可以开始开发了。
## 需求分析
目前计划开发的主要功能：  
- 浏览所有卡牌  
- 筛选、搜索卡牌  
- 查看单张卡牌详细信息
## 任务清单
* [x] 设计原型、基础界面
* [x] 确定技术路线
* [x] 搭建基础框架
* [x] 日志框架
* [x] 网络服务
* [x] 本地数据存储
* [x] 数据同步
* [ ] 屏幕适配
* [ ] 主界面框架
* [ ] 卡牌列表简单展示
* [ ] 富文本转换
* [ ] 图片显示及列表图片优化
* [ ] 卡牌详情页面
* [ ] 筛选面板
* [ ] 筛选功能
* [ ] 搜索面板
* [ ] 搜索功能
* [ ] 检测更新及bug收集反馈
## 技术路线：
网络：Retrofit2  
异步：RxJava2  
数据库：Realm  
图片：Glide  
统计、升级：Bugly

## 主要流程

数据同步流程

1. 启动App
2. Realm查询本地卡牌数据，两个卡包分别获取
3. 卡包数据为空，则从服务器获取卡包数据 -> 5
4. 卡包数据不为空，则直接展示所有的数据
5. 从服务器获取到新的卡包及卡牌数据后，先存储到本地数据库
6. 存储完毕后 -> 2

服务器流程

1. 检查本地是否保存了卡包的URl和过期时间
2. 如果存在，则判断时间是否过期，
3. 如果不存在，则 -> 6
4. 若时间过期，则 -> 6
5. 若时间未过期，则使用缓存的URL获取卡包数据
6. 获取卡包的CardSetUrl
7. 使用SP保存URL和过期时间

数据更新流程

1. 检测网络
2. 如果未联网，则不获取数据
3. 如果检测到联网，则尝试从服务器获取数据
4. 获取到新的数据后，更新数据库


## Api说明
先给接口传送门：[Artifact 卡牌Api](https://github.com/ValveSoftware/ArtifactDeckCode/blob/master/README.md)
Valve提供的接口，使用分为两步，首先根据BASE URL获取到缓存的CDN_HOST及URL，之后再用这个CDN_HOST和URL拼接起来获取卡牌列表集合，这个CDN_HOST及URL有一个有效期，在过期之后需要重新获取。而看了下卡包的json数据，头有点打…… 总结一下：

```json
{
  "card_set": {
    "version": 1,
    "set_info": {
      "set_id": 0,
      "pack_item_def": 0,
      "name": {
        "english": "Base Set" //卡包名称 多语言  如：基础套牌、武装号召等
      }
    },
    "card_list": [{

      "card_id": 4000,
      "base_card_id": 4000,
      "card_type": "Hero",//卡牌类型 目前有 Stronghold要塞 Pathing路线 Creep小兵 Item物品 Hero英雄 Passive Ability被动技能 Spell法术 Ability主动技能 Improvement强化
      "card_name": {
        "english": "Farvhan the Dreamer"// 卡牌名称 多语言 
      },
      "card_text": {
        "english": "Pack Leadership<BR>\nFarvhan the Dreamer's allied neighbors have +1 Armor."  //卡牌描述 多语言
      },
      "mini_image": {
        "default": "<url to png>" //小图
      },
      "large_image": {
        "default": "<url to png>" // 大图
      },
      "ingame_image": {
        "default": "<url to png>" // 游戏中icon
      },
      "is_green": true, // 卡牌颜色 对应的还有 is_blue is_red is_black
      "attack": 4,
      "hit_points": 10,
      "references": [{// 相关联的卡牌列表
          "card_id": 4002,
          "ref_type": "includes",// 关联类型 目前有 includes专属卡牌 references引用卡牌 passive_ability被动能力 active_ability主动能力
          "count": 3
        },
        {
          "card_id": 4001,
          "ref_type": "passive_ability"
        }
      ]


    },
    ..... more cards ....

    ]
  }
}
```


初次之外，还有一些特有的字段：


```json
"illustrator" 插画师
"rarity" 稀有度
"mana_cost" 需要费用
"sub_type" 物品的类型 目前有 Accessory视频 Armor护甲 Weapon武器 Consumable消耗品
"gold_cost" 物品价格
```

