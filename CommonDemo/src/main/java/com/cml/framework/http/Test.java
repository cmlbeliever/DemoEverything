package com.cml.framework.http;

import java.io.IOException;
import java.net.URLEncoder;

public class Test {
    public static final String METHOD_FPCX = "xforceplus.invoice.fpcx";

    public static void main(String[] args) throws IOException, InterruptedException {
        String server = "http://118.31.244.157:8080/litchi/ApiService";
        String system = "athenaTest";
        String method = "xforceplus.invoice.fpkj";
        String apiKey = "XCrdM3XTLagnPA+SRjpSTZuVVCJv8zt9I9s8WY7cfiEQGKPyFFaf2sOgbG4fHdyn";
        String invoiceType = "c";
        String url = server + "?systemType=" + system + "&method=" + method + "&APIKey=" + URLEncoder.encode(apiKey) + "&invoiceType=" + invoiceType;
        HttpClientUtil util = new HttpClientUtil();
        util.init();

        String body = "<business id=\"FPKJ\" comment=\"待开具\" > <REQUEST_COMMON_FPKJ class=\"REQUEST_COMMON_FPKJ\">  <FPQQLSH>20180529002742313H2x</FPQQLSH>  <KPZDBS>RTMart1001</KPZDBS>  <QDBZ>0</QDBZ>  <TZDBH></TZDBH>  <KPLX>0</KPLX>  <XSF_NSRSBH>500102000000888</XSF_NSRSBH>  <XSF_MC>增值税发票测试1</XSF_MC>  <BMB_BBH>27.0</BMB_BBH>  <ZSFS>0</ZSFS>  <KCE>0.00</KCE>  <XSF_DZDH>苏州工业园区东环路1500号 51269563511</XSF_DZDH>  <XSF_YHZH>建行苏州平江支行 32201988738051526401</XSF_YHZH>  <GMF_NSRSBH>91420115303471176F</GMF_NSRSBH>  <GMF_MC>武汉一品名酒店管理有限公司</GMF_MC>  <GMF_DZDH>武汉市江夏区大桥新区文化大道渔牧村文华佳园4号商业楼 027-81387999</GMF_DZDH>  <GMF_YHZH>汉口银行江夏支行 524011000034169</GMF_YHZH>  <KPR>海铭2</KPR>  <SKR>海铭</SKR>  <FHR>海铭01</FHR>  <YFP_DM></YFP_DM>  <YFP_HM></YFP_HM>  <JSHJ>26.20</JSHJ>  <HJJE>23.82</HJJE>  <HJSE>2.38</HJSE>  <BZ></BZ>  <HYLX>1</HYLX>  <BY1> </BY1>  <BY2> </BY2>  <BY3> </BY3>  <BY4> </BY4>  <BY5> </BY5>  <BY6> </BY6>  <BY7> </BY7>  <BY8> </BY8>  <BY9> </BY9>  <BY10> </BY10>  <COMMON_FPKJ_XMXXS>    <COMMON_FPKJ_XMXX>      <FPHXZ>0</FPHXZ>      <SPBM>1030204010000000000</SPBM>      <ZXBM></ZXBM>      <YHZCBS>0</YHZCBS>      <LSLBS></LSLBS>      <ZZSTSGL></ZZSTSGL>      <XMMC>*乳制品*巧克力A016</XMMC>      <GGXH></GGXH>      <DW>盒.</DW>      <XMSL>1</XMSL>      <XMDJ>26.2</XMDJ>      <XMJE>26.20</XMJE>      <SL>0.10</SL>      <SE>2.38</SE>      <BY1></BY1>      <BY2></BY2>      <BY3></BY3>      <BY4></BY4>      <BY5></BY5>      <BY6></BY6>      <HSBZ>0</HSBZ>    </COMMON_FPKJ_XMXX>  </COMMON_FPKJ_XMXXS></REQUEST_COMMON_FPKJ></business>";
        String resp = util.post(url, body, "GBK");
        System.out.println("===resp===>" + resp);

        Thread.sleep(3000);
        resp = util.post(url, getDownloadBody("20180529002742313H2x", "500102000000888"), "GBK");
        System.out.println("===resp===>" + resp);
    }

    public static String getDownloadBody(String fpqqlsh, String xsfNsrsbh) {

        StringBuffer s = new StringBuffer();
        s.append("<business id=\"FPCX\" comment=\"发票查询\" >").append("\n")
                .append("<REQUEST_COMMON_FPCX class=\"REQUEST_COMMON_FPCX\">").append("\n").append("<FPQQLSH>")
                .append(fpqqlsh).append("</FPQQLSH>").append("\n").append("<XSF_NSRSBH>").append(xsfNsrsbh)
                .append("</XSF_NSRSBH>").append("\n").append("</REQUEST_COMMON_FPCX>").append("</business>");

        return s.toString();
    }
}
