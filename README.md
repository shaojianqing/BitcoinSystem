# Introduction
Bitcoin should be seen as some kind of remarkable innovation for modern centralized financial system in this world. Bitcoin defines and operates a new kind of decentralized network which could define and deliver value in the form of bitcoin currency. 
Compared with modern centralized financial world, Bitcoin is completely decentralized network with the features of fairness, consistence and symmetry. Modern centralized financial system mainly consists of central bank and commercial bank system, and it
is based on national or government credit which is commonly unreliable and unstable in a long period and all over the world. Actually central bank plays the most centralized role in modern financial world, and it becomes the main and deep cause of financial crisis 
and unfair financial privilege, because central bank always conducts the monetary policy based on or at least influenced by some small parts of political groups, rather than most of our ordinary people. 

On the contrast, Bitcoin creates a completely new financial infrastructure for value definition and delivery, and this kind of financial infrastructure is internally based on the mathematics principle including hash digest mechanism, ECC cryptography and distributed consensus rule. Therefor Bitcoin 
network provides some kind of fair, consistent, symmetrical and stable financial infrastructure which could be seen as the revolutionary change and impact for modern centralized financial system.

Based on the above analysis and understanding of modern economics and financial principle, and the remarkable meaning of Bitcoin innovation, I finally made the decision that I could develop Bitcoin system and implement Bitcoin protocol entirely all by myself. 
Meanwhile, Java language is the most widely used computer language, so I develop Bitcoin system mainly with Java language. As the reference implementation of Bitcoin system, Bitcoin Core organizes and stores all the block and transaction data in the 
unit of block files. But in my implementation, all the blocks, transactions and UTXO accounts are organized and stored in the relational database. Here it is actually MySql database and InnoDB storage engine. As for my own purpose, my implementation is named as
BitcoinSystem, and it is regarded as my respect to Bitcoin and Satoshi. So BitcoinSystem is designed to run as a full node and could download all the blocks and transactions data from the entire worldwide Bitcoin network. The following parts describe all 
the detailed and precise system design and technical mechanism.

# General Architecture

![](https://github.com/shaojianqing/BitcoinSystem/blob/master/doc/images/general_architecture.png)
> General Architecture Design

|Seq|Module|Description|
|:-- |:--------------- |:-------------------------------------------|
|1|Context Core | |
|2|ORM Facility | |
|3|P2P Network | |
|4|Messages | |
|5|Script Runtime | |
|6|Services | |
|7|Wallet Core | |
|8|Miner | |
|9|Utility Tools | |
|10|Crypto | |
|11|Entities | |
|12|API Server | |
|13|GUI Interface | |

# Database Entity Design

![](https://github.com/shaojianqing/BitcoinSystem/blob/master/doc/images/database_design.png)
> Database Structure Design

|Seq|Module|Description|
|:-- |:--------------- |:-------------------------------------------|
|1|block | |
|2|transaction | |
|3|transaction_block | |
|4|transaction_spend | |
|5|transaction_input | |
|6|transaction_output | |
|7|transaction_address | |

# Block and Transaction Structure
 