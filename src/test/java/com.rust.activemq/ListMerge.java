 /*
  * Package com.rust.activemq
  * FileName: ListMerge
  * Author:   Rust
  * Date:     2018/6/22 22:51
  */
 package com.rust.activemq;

 import com.google.common.base.Predicate;
 import com.google.common.collect.Collections2;
 import org.junit.Test;

 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.List;

 /**
  * FileName:    ListMerge
  * Author:      Rust
  * Date:        2018/6/22
  * Description:
  */
 public class ListMerge {


  @Test
  public void test() {
  // 查cip全量

   List<CipInfo> cipInfos = new ArrayList<>();


   for (int i = 0; i < 5; i++) {
    CipInfo c = new CipInfo();

    c.setEmail(i + "zhangsan@qq.com");
    c.setName("zhangsan");
    c.setOperatorId(String.valueOf(i));
    c.setStatus(i % 2 == 0 ? 0 : 1);
    cipInfos.add(c);
   }

   List<MbspInfo> mbspInfos = new ArrayList<>();

   for (int i = 0; i < 5; i++) {
    MbspInfo m = new MbspInfo();
    m.setId(1 + i);
    m.setUserId(String.valueOf(i));
    m.setDelStatus(i % 2 == 0 ? "0" : "1");
    mbspInfos.add(m);
   }
   final int[] num = {0};
   List<CipInfo> readyListIgnoreBanStatus = new ArrayList<>();
   Collection<CipInfo> notReadyListNotBanStatus = Collections2.filter(cipInfos, new Predicate<CipInfo>() {
    @Override
    public boolean apply(CipInfo cipInfo) {

     for (MbspInfo mbspInfo : mbspInfos) {
      if (mbspInfo.getUserId().equals(cipInfo.getOperatorId())) {
       // 增加id
       cipInfo.setIdentityNo(String.valueOf(mbspInfo.getId()));
       if ("0".equals(mbspInfo.getDelStatus())) {
        // 订阅状态无效的，未订阅的
        if ("0".equals(String.valueOf(cipInfo.getStatus()))) {
         // 封禁状态正常，未封禁的
         return true;
        }
       }else if ("1".equals(mbspInfo.getDelStatus())) {
        // 订阅过的
        ++num[0];
        readyListIgnoreBanStatus.add(cipInfo);
       }

      }
     }
     return false;
    }
   });
  // cipCollect 全是无效的(未订阅的)，（封禁状态正常，未封禁的）


  //选出 订阅过的，无视封禁状态，要有MBSP的ID

/*   Collection<CipInfo> readyListIgnoreBanStatus = Collections2.filter(cipInfos, new Predicate<CipInfo>() {
    @Override
    public boolean apply(CipInfo cipInfo) {
     for (MbspInfo mbspInfo : mbspInfos) {
      if (mbspInfo.getUserId().equals(cipInfo.getOperatorId())) {
       if ("1".equals(mbspInfo.getDelStatus())) {
        // 订阅过的
        return true;
       }
      }
     }
     return false;
    }
   });*/


   System.out.println(notReadyListNotBanStatus);

   System.out.println(readyListIgnoreBanStatus);

   System.out.println(num[0]);











  }

   class MbspInfo {
   private String userId;
   private long id;
   private String delStatus;

   public String getDelStatus() {
    return delStatus;
   }

   public void setDelStatus(String delStatus) {
    this.delStatus = delStatus;
   }

   public String getUserId() {
    return userId;
   }

   public void setUserId(String userId) {
    this.userId = userId;
   }

   public long getId() {
    return id;
   }

   public void setId(long id) {
    this.id = id;
   }
  }


   class CipInfo{
   private String name;
   private String email;
   private String operatorId;
   private String identityNo;
   private int status;

   public String getIdentityNo() {
    return identityNo;
   }

   public void setIdentityNo(String identityNo) {
    this.identityNo = identityNo;
   }

   public String getName() {
    return name;
   }

   public void setName(String name) {
    this.name = name;
   }

   public String getEmail() {
    return email;
   }

   public void setEmail(String email) {
    this.email = email;
   }

   public String getOperatorId() {
    return operatorId;
   }

   public void setOperatorId(String operatorId) {
    this.operatorId = operatorId;
   }

   public int getStatus() {
    return status;
   }

   public void setStatus(int status) {
    this.status = status;
   }

   @Override
   public String toString() {
    return "CipInfo{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", operatorId='" + operatorId + '\'' +
            ", identityNo='" + identityNo + '\'' +
            ", status=" + status +
            '}';
   }
  }

 }
