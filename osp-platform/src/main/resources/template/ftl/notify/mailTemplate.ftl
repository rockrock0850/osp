<#--
/*
 * Copyright (c) 2017 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 * 
 * 訊息通知（電子郵件）
 */
-->
<#t><table cellpadding=0 
<#t>       style='border-collapse:collapse;border:none;mso-border-alt:solid #7BA0CD 1.0pt;mso-border-themecolor:accent1;mso-border-themetint:191;mso-yfti-tbllook:1184; mso-padding-alt:0cm 5.4pt 0cm 5.4pt'>
<#t><thead>
<#t><tr style='mso-yfti-irow:-1;mso-yfti-firstrow:yes'>
<#t>  <td width='232' valign='top' style='width:139.35pt;border:solid #7BA0CD 1.0pt;
<#t>  mso-border-themecolor:accent1;mso-border-themetint:191;border-right:none;
<#t>  background:#4F81BD;mso-background-themecolor:accent1;padding:0cm 5.4pt 0cm 5.4pt'>
<#t>	進件時間
<#t>  </td>
<#t>  <td width='232' valign='top' style='width:139.35pt;border:solid #7BA0CD 1.0pt;
<#t>  mso-border-themecolor:accent1;mso-border-themetint:191;border-right:none;
<#t>  background:#4F81BD;mso-background-themecolor:accent1;padding:0cm 5.4pt 0cm 5.4pt'>
<#t>	產品類別
<#t>  </td>
<#t>  <td width='232' valign='top' style='width:139.35pt;border:solid #7BA0CD 1.0pt;
<#t>  mso-border-themecolor:accent1;mso-border-themetint:191;border-right:none;
<#t>  background:#4F81BD;mso-background-themecolor:accent1;padding:0cm 5.4pt 0cm 5.4pt'>
<#t>	門號
<#t>  </td>
<#t>  <td width='232' valign='top' style='width:139.35pt;border:solid #7BA0CD 1.0pt;
<#t>  mso-border-themecolor:accent1;mso-border-themetint:191;border-right:none;
<#t>  background:#4F81BD;mso-background-themecolor:accent1;padding:0cm 5.4pt 0cm 5.4pt'>
<#t>	授權原因
<#t>  </td>
<#t>  <td width='232' valign='top' style='width:139.35pt;border:solid #7BA0CD 1.0pt;
<#t>  mso-border-themecolor:accent1;mso-border-themetint:191;border-right:none;
<#t>  background:#4F81BD;mso-background-themecolor:accent1;padding:0cm 5.4pt 0cm 5.4pt'>
<#t>	案件狀態
<#t>  </td>
<#t>  <td width='232' valign='top' style='width:139.35pt;border:solid #7BA0CD 1.0pt;
<#t>  mso-border-themecolor:accent1;mso-border-themetint:191;border-right:none;
<#t>  background:#4F81BD;mso-background-themecolor:accent1;padding:0cm 5.4pt 0cm 5.4pt'>
<#t>	處理結果
<#t>  </td>
<#t>  <td width='232' valign='top' style='width:139.35pt;border:solid #7BA0CD 1.0pt;
<#t>  mso-border-themecolor:accent1;mso-border-themetint:191;border-right:none;
<#t>  background:#4F81BD;mso-background-themecolor:accent1;padding:0cm 5.4pt 0cm 5.4pt'>
<#t>	處理原因
<#t>  </td>
<#t>  <td width='232' valign='top' style='width:139.35pt;border:solid #7BA0CD 1.0pt;
<#t>  mso-border-themecolor:accent1;mso-border-themetint:191;border-right:none;
<#t>  background:#4F81BD;mso-background-themecolor:accent1;padding:0cm 5.4pt 0cm 5.4pt'>
<#t>	備註
<#t>  </td>
<#t></tr>
<#t></thead>
<#t><tbody>
<#t>
<#list gridData as data>
<#t><tr style='mso-yfti-irow:1;mso-yfti-lastrow:yes'>
<#t>
<#t><td width=232 valign=top style='width:139.35pt;border-top:none;border-left:
<#t>  solid #7BA0CD 1.0pt;mso-border-left-themecolor:accent1;mso-border-left-themetint:
<#t>  191;border-bottom:solid #7BA0CD 1.0pt;mso-border-bottom-themecolor:accent1;
<#t>  mso-border-bottom-themetint:191;border-right:none;mso-border-top-alt:solid #7BA0CD 1.0pt;
<#t>  mso-border-top-themecolor:accent1;mso-border-top-themetint:191;background:
<#t>  #D3DFEE;mso-background-themecolor:accent1;mso-background-themetint:63;
<#t>  padding:0cm 5.4pt 0cm 5.4pt'>
<#t>   ${data[0]} <#-- 進件時間 -->
<#t></td>
<#t>
<#t><td width=232 valign=top style='width:139.35pt;border-top:none;border-left:
<#t>  solid #7BA0CD 1.0pt;mso-border-left-themecolor:accent1;mso-border-left-themetint:
<#t>  191;border-bottom:solid #7BA0CD 1.0pt;mso-border-bottom-themecolor:accent1;
<#t>  mso-border-bottom-themetint:191;border-right:none;mso-border-top-alt:solid #7BA0CD 1.0pt;
<#t>  mso-border-top-themecolor:accent1;mso-border-top-themetint:191;background:
<#t>  #D3DFEE;mso-background-themecolor:accent1;mso-background-themetint:63;
<#t>  padding:0cm 5.4pt 0cm 5.4pt'>
<#t>   ${data[1]} <#-- 產品類別 -->
<#t></td>
<#t>
<#t><td width=232 valign=top style='width:139.35pt;border-top:none;border-left:
<#t>  solid #7BA0CD 1.0pt;mso-border-left-themecolor:accent1;mso-border-left-themetint:
<#t>  191;border-bottom:solid #7BA0CD 1.0pt;mso-border-bottom-themecolor:accent1;
<#t>  mso-border-bottom-themetint:191;border-right:none;mso-border-top-alt:solid #7BA0CD 1.0pt;
<#t>  mso-border-top-themecolor:accent1;mso-border-top-themetint:191;background:
<#t>  #D3DFEE;mso-background-themecolor:accent1;mso-background-themetint:63;
<#t>  padding:0cm 5.4pt 0cm 5.4pt'>
<#t>   ${data[2]} <#-- 門號 -->
<#t></td>
<#t>
<#t><td width=232 valign=top style='width:139.35pt;border-top:none;border-left:
<#t>  solid #7BA0CD 1.0pt;mso-border-left-themecolor:accent1;mso-border-left-themetint:
<#t>  191;border-bottom:solid #7BA0CD 1.0pt;mso-border-bottom-themecolor:accent1;
<#t>  mso-border-bottom-themetint:191;border-right:none;mso-border-top-alt:solid #7BA0CD 1.0pt;
<#t>  mso-border-top-themecolor:accent1;mso-border-top-themetint:191;background:
<#t>  #D3DFEE;mso-background-themecolor:accent1;mso-background-themetint:63;
<#t>  padding:0cm 5.4pt 0cm 5.4pt'>
<#t>   ${data[3]} <#-- 授權原因 -->
<#t></td>
<#t>
<#t><td width=232 valign=top style='width:139.35pt;border-top:none;border-left:
<#t>  solid #7BA0CD 1.0pt;mso-border-left-themecolor:accent1;mso-border-left-themetint:
<#t>  191;border-bottom:solid #7BA0CD 1.0pt;mso-border-bottom-themecolor:accent1;
<#t>  mso-border-bottom-themetint:191;border-right:none;mso-border-top-alt:solid #7BA0CD 1.0pt;
<#t>  mso-border-top-themecolor:accent1;mso-border-top-themetint:191;background:
<#t>  #D3DFEE;mso-background-themecolor:accent1;mso-background-themetint:63;
<#t>  padding:0cm 5.4pt 0cm 5.4pt'>
<#t>   ${data[4]} <#-- 案件狀態 -->
<#t></td>
<#t>
<#t><td width=232 valign=top style='width:139.35pt;border-top:none;border-left:
<#t>  solid #7BA0CD 1.0pt;mso-border-left-themecolor:accent1;mso-border-left-themetint:
<#t>  191;border-bottom:solid #7BA0CD 1.0pt;mso-border-bottom-themecolor:accent1;
<#t>  mso-border-bottom-themetint:191;border-right:none;mso-border-top-alt:solid #7BA0CD 1.0pt;
<#t>  mso-border-top-themecolor:accent1;mso-border-top-themetint:191;background:
<#t>  #D3DFEE;mso-background-themecolor:accent1;mso-background-themetint:63;
<#t>  padding:0cm 5.4pt 0cm 5.4pt'>
<#t>   ${data[5]} <#-- 處理結果 -->
<#t></td>
<#t>
<#t><td width=232 valign=top style='width:139.35pt;border-top:none;border-left:
<#t>  solid #7BA0CD 1.0pt;mso-border-left-themecolor:accent1;mso-border-left-themetint:
<#t>  191;border-bottom:solid #7BA0CD 1.0pt;mso-border-bottom-themecolor:accent1;
<#t>  mso-border-bottom-themetint:191;border-right:none;mso-border-top-alt:solid #7BA0CD 1.0pt;
<#t>  mso-border-top-themecolor:accent1;mso-border-top-themetint:191;background:
<#t>  #D3DFEE;mso-background-themecolor:accent1;mso-background-themetint:63;
<#t>  padding:0cm 5.4pt 0cm 5.4pt'>
<#t>   ${data[6]} <#-- 處理原因 -->
<#t></td>
<#t>
<#t><td width=232 valign=top style='width:139.35pt;border-top:none;border-left:
<#t>  solid #7BA0CD 1.0pt;mso-border-left-themecolor:accent1;mso-border-left-themetint:
<#t>  191;border-bottom:solid #7BA0CD 1.0pt;mso-border-bottom-themecolor:accent1;
<#t>  mso-border-bottom-themetint:191;border-right:none;mso-border-top-alt:solid #7BA0CD 1.0pt;
<#t>  mso-border-top-themecolor:accent1;mso-border-top-themetint:191;background:
<#t>  #D3DFEE;mso-background-themecolor:accent1;mso-background-themetint:63;
<#t>  padding:0cm 5.4pt 0cm 5.4pt'>
<#t>   ${data[7]} <#-- 備註 -->
<#t></td>
<#t>
<#t></tr>
<#t>
</#list>
<#t></tbody>
<#t></table>
<#t>
<#t><br />
<#t><br />
<#t>
<#t><p style="color: red;font-weight: bolder;">備註：本信件為系統自動發送，請勿回信</p>
