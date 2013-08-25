package com.gmcc.util;

import com.gmcc.util.AppContentGmcc;

public class AppContentWms extends AppContentGmcc {
	
	//开发框架DEMO
	public static final String TEST_BILL_STATUS = "billStatus";
	//配送状态
	public static final String LOGISTICS_SENDRSLTFEED_STATUS = "sendResult";
	//配送失败类型
	public static final String SEND_FAIL_TYPE_STATUS = "sendFailType";
	//运单状态
	public static final String SEND_TRANSPORT_STATUS = "transportStatus";
	//库区类型
	public static final String STOCK_AREA_TYPE = "stockAreaType" ;
	//是否实物资源
	public static final String PRODUCT_IS_GOODS_1 = "1" ;
	public static final String PRODUCT_IS_GOODS_0 = "0" ;
	public static final String PRODUCT_IS_GOODS_1_NAME = "是" ;
	public static final String PRODUCT_IS_GOODS_0_NAME = "否" ;
	
	//订单类型
	public static final String ORDER_TYPE = "orderType";	
	
	//协议模板
	public static final String PROTOCAL_TEMP = "protocalTemp";
	
	public static final String PROTOCAL_TEMP_0_MZONE = "0";//动感地带
	public static final String PROTOCAL_TEMP_1_GOTONE = "1";//全球通
	public static final String PROTOCAL_TEMP_2_SZX = "2";//神州行
	public static final String PROTOCAL_TEMP_3_3G = "3";//3G协议
	
	
	//原始单据类型
	public static final String ORIGINAL_INVOICE_TYPE = "originalInvoiceType" ;
	public static final String ORIGINAL_INVOICE_TYPE_2_RP_RECEIVE="2_RP_RECEIVE"; 
	public static final String ORIGINAL_INVOICE_TYPE_3_SR_RECEIVE="3_SR_RECEIVE";
	public static final String ORIGINAL_INVOICE_TYPE_5_STOCKMOVE="5_STOCKMOVE";
	public static final String ORIGINAL_INVOICE_TYPE_11_PICKING="11_PICKING";
	public static final String ORIGINAL_INVOICE_TYPE_13_PICKUP="13_PICKUP";
    public static final String ORIGINAL_INVOICE_TYPE_14_PICKDOWN="14_PICKDOWN";	
    public static final String ORIGINAL_INVOICE_TYPE_15_SEND = "15_SEND";
    public static final String ORIGINAL_INVOICE_TYPE_17_PORETURN="17_PORETURN";
    
    public static final String ORIGINAL_INVOICE_TYPE_20_RES_DAMAGE="20_RES_DAMAGE";  //资源报损单
    public static final String ORIGINAL_INVOICE_TYPE_21_RES_GOOD="21_RES_GOOD";      //资源转正单
    public static final String ORIGINAL_INVOICE_TYPE_22_RES_LOST="22_RES_LOST";      //资源报失单
    public static final String ORIGINAL_INVOICE_TYPE_23_RES_RETURN="23_RES_RETURN";  //失物找回单
        
    //收货单状态
    public static final String GOODSRECV_STATUS = "goodsRecvStatus" ;
    public static final String GOODSRECV_STATUS_1_RECEIVE_DRAFT = "1_RECEIVE_DRAFT" ;
    public static final String GOODSRECV_STATUS_2_RECEIVE_CONFIRMED = "2_RECEIVE_CONFIRMED" ;
    public static final String GOODSRECV_STATUS_3_RECEIVE_REFUSED = "3_RECEIVE_REFUSED" ;
    public static final String GOODSRECV_STATUS_4_RECEIVE_CANCELED = "4_RECEIVE_CANCELED" ;
    public static final String GOODSRECV_STATUS_5_RECEIVE_DONE = "5_RECEIVE_DONE" ;
    
    //移位单状态
    public static final String MOVE_STATUS = "moveStatus" ;
    public static final String MOVE_STATUS_1_MOVE_DRAFT = "1_MOVE_DRAFT" ;  //起草
    public static final String MOVE_STATUS_2_MOVE_CONFIRMED = "2_MOVE_CONFIRMED" ;  //审批通过
    public static final String MOVE_STATUS_3_MOVE_REFUSED = "3_MOVE_REFUSED" ;  //审批拒绝
    public static final String MOVE_STATUS_4_MOVE_CANCELED = "4_MOVE_CANCELED" ;  //作废
    public static final String MOVE_STATUS_5_MOVE_PROCESSING = "5_MOVE_PROCESSING" ;  //作业处理中
    public static final String MOVE_STATUS_6_MOVE_DONE = "6_MOVE_DONE" ;  //移货完成
    
    //商品性质
    public static final String GOODS_PROPERTY = "goodsProperty" ; 
    public static final String GOODS_PROPERTY_1_GOOD = "1_GOOD" ; //正品
    public static final String GOODS_PROPERTY_2_DAMAGE = "2_DAMAGE" ;//残品
    public static final String GOODS_PROPERTY_3_LOST = "3_LOST" ;//丢失
    
    //库区类型
    public static final String STOCK_AREA_TYPE_OUTBOUND = "OUTBOUND" ; //出库区
    public static final String STOCK_AREA_TYPE_YIELD = "YIELD" ; //加工区
    public static final String STOCK_AREA_TYPE_PACKING = "PACKING" ; //包装区
    public static final String STOCK_AREA_TYPE_STORAGE = "STORAGE" ; //存储区
    public static final String STOCK_AREA_TYPE_PICKING = "PICKING" ; //拣货区
    public static final String STOCK_AREA_TYPE_INBOUND = "INBOUND" ; //收货区
    public static final String STOCK_AREA_TYPE_TEMP = "TEMP" ; //暂存区
    public static final String STOCK_AREA_TYPE_DAMAGE = "DAMAGE" ; //残品区
    public static final String STOCK_AREA_TYPE_LOGICAL = "LOGICAL" ; //虚拟库区
    
    
    
    //资源类型
    public static final String RES_TYPE = "resType" ;
    
    //产品类型
    public static final String PRODUCTTYPE = "productType";
	/**
	 * 产品资源类型－手机
	 */
	public static final String PRODUCT_RESTYPE_MOBILE="001";
	/**
	 * 产品资源类型－G3信息机
	 */
	public static final String PRODUCT_RESTYPE_G3="002";
	/**
	 * 产品资源类型－套卡
	 */
	public static final String PRODUCT_RESTYPE_SUITE="003";
	/**
	 * 产品资源类型－白卡
	 */
	public static final String PRODUCT_RESTYPE_WHITE="004";
	/**
	 * 产品资源类型－其他
	 */
	public static final String PRODUCT_RESTYPE_OTHER="005";
	/**
	 * 产品资源类型－G3号码
	 */
	public static final String PRODUCT_RESTYPE_G3NUM="006";
	/**
	 * 产品资源类型—上网卡
	 */
	public static final String PRODUCT_RESTYPE_WEBCARD="007";
//	public static final int PAGE_SIZE = 10;

    
    //资源状态
    public static final String RES_INVENTORY_STATUS = "resInventoryStatus" ;
    public static final String RES_INVENTORY_STATUS_1_AVAILABLE = "1_AVAILABLE" ; //可用
    public static final String RES_INVENTORY_STATUS_2_UNAVAILABLE = "2_UNAVAILABLE" ; //不可用 
    public static final String RES_INVENTORY_STATUS_6_WAIT_CHECK = "6_WAIT_CHECK" ;  //待检
    public static final String RES_INVENTORY_STATUS_OTHER_LOCK = "9_OTHER_LOCK" ; //其它渠道锁定
    
    //校验状态
    public static final String VALIDATESTATUS = "validateStatus" ;
    public static final String VALIDATESTATUS_1_WAIT_CHECK = "1_WAIT_CHECK" ;  //待检
    public static final String VALIDATESTATUS_2_CHECK_PASS = "2_CHECK_PASS" ;  //校验通过
    public static final String VALIDATESTATUS_3_CHECK_NOTPASS = "3_CHECK_NOTPASS" ;  //校验不通过
    
    //上下架关联单类型
	public static final String PICK_UP_DOWN_LINK = "pickupDownLink" ;
	public static final String PICK_UP_DOWN_LINK_01 = "01" ; //退补上架
	public static final String PICK_UP_DOWN_LINK_02 = "02" ; //销退上架
	public static final String PICK_UP_DOWN_LINK_03 = "03" ; //移位上架
	public static final String PICK_UP_DOWN_LINK_04 = "04" ; //移位下架
	public static final String PICK_UP_DOWN_LINK_05 = "05" ; //进退货下架
	public static final String PICK_UP_DOWN_LINK_06 = "06" ; //销售下架
	public static final String PICK_UP_DOWN_LINK_07 = "07" ; //调拨上架
	public static final String PICK_UP_DOWN_LINK_08 = "08" ; //调拨下架


	//上下架单状态
	public static final String PICK_UP_DOWN_STATUS = "pickUpDownStatus" ;
	public static final String PICK_UP_DOWN_STATUS_1_PICKUPDOWN_DRAFT = "1_PICKUPDOWN_DRAFT" ;  //起草
	public static final String PICK_UP_DOWN_STATUS_2_PICKING_UPDOWN = "2_PICKING_UPDOWN" ;   //正在上架/下架
	public static final String PICK_UP_DOWN_STATUS_3_PICKUPDOWN_DONE = "3_PICKUPDOWN_DONE" ; //上架/下架完成
	
	//拣选单状态
	public static final String ORDERPICK_STATUS = "pickStatus" ;
	public static final String ORDERPICK_STATUS_1_WAIT_PROCESS = "1_WAIT_PROCESS"; // 仓库待处理
	public static final String ORDERPICK_STATUS_2_WAIT_PICKING = "2_WAIT_PICKING"; // 已制分拣单待拣货
	public static final String ORDERPICK_OUTBOUND_WAITSEND = "11_OUTBOUND_WAITSEND"; //待收货
	public static final String ORDERPICK_SEND_DONE = "12_SEND_DONE"; // 已发货
	
	//补货单状态
	public static final String SUPPLYSTATUS_2_PO_CONFIRMED = "2_PO_CONFIRMED"; //审批通过
	
	//汇总制单策略
	public static final String PICKGATHER_POLICY = "pickgatherPolicy" ;
	public static final String PICKGATHER_POLICY_LOGISTICS = "1_LOGISTICS" ;	
	public static final String PICKGATHER_POLICY_CITY = "2_CITY" ;
	public static final String PICKGATHER_POLICY_PROTOCOL = "3_PROTOCOL" ;
	public static final String PICKGATHER_POLICY_INVOICE = "4_INVOICE" ;
	
	public static final String PICKGATHER_POLICY_MAX_SIZE = "pickgatherPolicyMaxSize" ;
	public static final String PICKGATHER_POLICY_MAX_SIZE_10 = "maxSize";
	
	//生产效率监控环节
	public static final String EFFICENCY_MONITOR_TYPE = "efficencyMonitorType";
	public static final String EFFICENCY_MONITOR_TYPE_0_IMPORT_ORDER = "0_IMPORT_ORDER";//订单导入
	public static final String EFFICENCY_MONITOR_TYPE_1_MAKE_PICKFORM = "1_MAKE_PICKFORM";//制单
	public static final String EFFICENCY_MONITOR_TYPE_2_PICKING = "2_PICKING";//拣货
	public static final String EFFICENCY_MONITOR_TYPE_3_QUALITY_CHECK = "3_QUALITY_CHECK";//质检
	public static final String EFFICENCY_MONITOR_TYPE_4_SEND_GOODS = "4_SEND_GOODS";//发货
	public static final String EFFICENCY_MONITOR_TYPE_5_LORECEIVE = "5_LORECEIVE";//物流收货
	public static final String EFFICENCY_MONITOR_TYPE_6_TRANS = "6_TRANS";//物流配送
		
	public static final String PRIVIDER_CODE = "P" ;
	public static final String GOODSSUPPLY_CODE = "RP" ;
	public static final String STOCKTAKING_CODE = "CT" ;
	public static final String LOGISTICS_CODE = "L" ;
	public static final String WAREHOUSE_CODE = "WH" ;
	public static final String PICKGATHERNUM_CODE = "PS" ;
	
	public final static String BILL_SN_RECV = "IN";
	public final static String BILL_SN_UP = "UP";
	public final static String BILL_SN_DN = "DN";
	public final static String BILL_SN_MV = "MV";
	public final static String BILL_SN_IR = "IR";
	public final static String BILL_SN_PK = "PK";
	public final static String BILL_SN_TS = "TS";
	
	// Enabled and disabled
	public final static String ENABLED = "1";
	public final static String DISABLED = "0";
	
	// 订单类型
	public final static String ORDER_NOMAL = "01"; //普通订单
	public final static String ORDER_RETURN = "02"; //退货单
	public final static String ORDER_REPLACE = "03"; //换货单
	
	// 订单处理类型
	public final static String ORDER_DEAL_NOMAL = "1"; //发货订单
	public final static String ORDER_DEAL_RETURN = "2"; //回收单
	
	// 订单状态
	public final static String ORDERSTATUS = "orderStatus";
	public final static String ORDERSTATUS_1_WAIT_PROCESS = "1_WAIT_PROCESS";  //仓库待处理
	public final static String ORDERSTATUS_2_WAIT_PICKING = "2_WAIT_PICKING";  //制单已制分拣单待拣货
	public final static String ORDERSTATUS_3_PICKING    = "3_PICKING";       //正在拣货
	public final static String ORDERSTATUS_4_PICKING_DONE = "4_PICKING_DONE";  //拣货完成待质检
	public final static String ORDERSTATUS_5_PICKING_EXCEPTION = "5_PICKING_EXCEPTION";  //拣货异常待处理
	public final static String ORDERSTATUS_6_PICKING_CANCELED = "6_PICKING_CANCELED";  //拣货取消
	public final static String ORDERSTATUS_7_QUALITY_CHECKING = "7_QUALITY_CHECKING";  //正在质检
	public final static String ORDERSTATUS_8_QUALITY_DONE = "8_QUALITY_DONE";  //质检完成待包装
	public final static String ORDERSTATUS_9_PACKING = "9_PACKING";  //正在包装
	public final static String ORDERSTATUS_10_PACKING_DONE = "10_PACKING_DONE";  //包装完成待发货
	public final static String ORDERSTATUS_11_OUTBOUND_WAITSEND = "11_OUTBOUND_WAITSEND";  //待配送
	public final static String ORDERSTATUS_12_SENDING = "12_SENDING";  //配送中
	public final static String ORDERSTATUS_13_SEND_DELAY = "13_SEND_DELAY";  //配送延迟
	public final static String ORDERSTATUS_14_SEND_FAIL = "14_SEND_FAIL";  //配送失败
	public final static String ORDERSTATUS_15_SEND_FINISH = "15_SEND_FINISH";  //配送完成
	public final static String ORDERSTATUS_16_WAIT_RETURN = "16_WAIT_RETURNING";  //待回收
	public final static String ORDERSTATUS_17_RETURNING = "17_RETURNING";  //回收中
	public final static String ORDERSTATUS_18_RETURN_FINISH = "18_RETURN_FINISH";  //回收完成
	public final static String ORDERSTATUS_19_ORDER_WMS_FINISH = "19_ORDER_WMS_FINISH";  //订单完结
	public final static String ORDERSTATUS_20_SEND_FAIL_RETURN = "20_SEND_FAIL_RETURN";  //配送失败已回仓
	
	//运单类型
	public static final String sendType = "sendType";
	public final static String TRANSPORT_NOMAL = "1_NORMAL"; //正常配送运单
	public final static String TRANSPORT_RECYCLE = "2_RECYCLE"; //回收订单运单
	
	// 运单状态
	public final static String TRANSPORTSTATUS_14_SEND_FAIL = "14_SEND_FAIL"; // 配送失败
	public final static String TRANSPORTSTATUS_16_WAIT_RETURNING = "16_WAIT_RETURNING"; // 待回收
	public final static String TRANSPORTSTATUS_17_RETURNING = "17_RETURNING"; // 回收中
	
	// 处理标识
	public final static Integer NO_DEAL = 0; //未处理
	public final static Integer DEALING = 1; //正在处理
	public final static Integer DEAL_SUCCESS = 2; //处理成功
	public final static Integer DEAL_FAIL = 3; //处理失败
	
	
	// 库存来源
	public final static String INVENTORY_SOURCE_TOTAL = "0"; // 从仓储系统同步总量 
	public final static String INVENTORY_SOURCE_DETAIL = "1"; // 从仓储系统同步明细
	
	// 产品目录级别
	public final static String CATALOG_LEVEL = "catalogLevel"; //产品目录级别
	public final static String CATALOG_LEVEL1 = "Level1"; //一级目录
	public final static String CATALOG_LEVEL2 = "Level2"; //二级目录
	
	public final static String FILE_PATH = "BOSS_FTP_PATH"; //BOSS ftp路径
	public final static String CALL_CENTER_FTP_EXPORT_PATH = "CALL_CENTER_FTP_EXPORT_PATH"; // Call center 导出路径
	public final static String CALL_CENTER_FTP_IMPORT_PATH = "CALL_CENTER_FTP_IMPORT_PATH"; // Call center 导入路径
	
	
	// Boss导入类型
	public final static String BOSS_IMPORT_3G = "01"; // 3G号码资源
	public final static String BOSS_IMPORT_IMEI = "02"; // IMEI资源
	public final static String BOSS_IMPORT_SIMCARD = "03"; // SIM卡资源
	public final static String BOSS_IMPORT_CARDINFO = "04"; // 套卡
	
	// Boss文件类型
	public final static String BOSS_TYPE_3G = "3GINFO";
	public final static String BOSS_TYPE_IMEI = "TerminalInfo";
	public final static String BOSS_TYPE_SIMCARD = "SIMCARDINFO";
	public final static String BOSS_TYPE_CARDINFO = "CardInfo";
	
	// Boss导入模式
	public final static String BOSS_MODE_INCREMENTAL = "0"; // 增量
	public final static String BOSS_MODE_FULL = "1"; // 全量
	
	public final static String ACTIONTYPE = "actionType";
	public final static String BOSS_EOF = "HDR2";
	
	// 短信模板类型
	public final static Integer SMS_MODE_LOGISTICS_RECEIVE = 1;
	public final static Integer SMS_MODE_ACTIVATE_SUCCESS = 2;
	public final static Integer SMS_MODE_ACTIVATE_FAIL = 3;
	public final static Integer SMS_MODE_ACTIVATE_SYSTEM_ERR = 4;
	
	// 短信处理
	public final static String SMS_REPLY = "smsReply";
	public final static String SMS_REPLY_DEALTIME = "dealTime"; // 当日处理截至时间
	public final static String SMS_REPLY_STOCKTAKINGDAY = "stockTakingDay"; // 当月盘点日
	
	// 发票、收据
	public final static String ISINVOICE_INVOICE = "1"; // 发票
	public final static String ISINVOICE_RECEIPT = "2"; // 收据
	public final static String ISINVOICE_INVOICE_AND_RECEIPT = "3"; // 发票+收据
	
	// 地市
	public final static String CITY_ALL = "-1"; //全省
	
	/**
	 * 货位编码中货架层长度
	 */
	public final static Long GOODSPOS_LAYER_LENGTH=2L; 
	/**
	 * 货位编码中货位号长度
	 */
	public final static Long GOODSPOS_POS_LENGTH=3L;
	/**
	 * 货架编码中行（列）长度
	 */
	public final static int GOODSRACK_LINE_LENGTH=3; 
	/**
	 * 货位编码中货架号长度
	 */
	public final static int GOODSRACK_NUMBER_LENGTH=3;
	//盘点状态
	/**
	 * 盘点状态组名
	 */
	public static final String TAKING_STATUS_GROUPNAME = "stockCountStatus";
	/**
	 * 盘点状态－备份
	 */
	public static final String TAKING_STATUS_COUNTBAK = "1_COUNTBAK";
	
	/**
	 * 盘点状态－盘点登记
	 */
	public static final String TAKING_STATUS_COUNTSUBMIT = "2_COUNTENROLL";
	
	/**
	 * 盘点状态－审批通过
	 */
	public static final String TAKING_STATUS_COUNTAPPROVED = "3_COUNTAPPROVED";
	/**
	 * 盘点状态－审批拒绝
	 */
	public static final String TAKING_STATUS_COUNTREFUESE = "4_COUNTREFUESE";
	/**
	 * 盘点状态－作废
	 */
	public static final String TAKING_STATUS_COUNTCANCEL = "5_COUNTCANCEL";
	
	//补货单状态
	/**
	 * 补货单状态字典表组名
	 */
	public static final String GOODSSUPPLY_STATUS_GROUP="purchaseStatus";
	/**
	 * 补货单状态－起草
	 */
	public static final String GOODSSUPPLY_STATUS_DRAFT="1_PO_DRAFT";
	
	/**
	 * 补货单状态－审批通过
	 */
	public static final String GOODSSUPPLY_STATUS_CONFIRMED="2_PO_CONFIRMED";
	/**
	 * 补货单状态－审批拒绝
	 */
	public static final String GOODSSUPPLY_STATUS_REFUSED="3_PO_REFUSED";
	/**
	 * 补货单状态－撤销
	 */
	public static final String GOODSSUPPLY_STATUS_CANCELED="4_PO_CANCELED";
	/**
	 * 补货单状态－补货完成
	 */
	public static final String GOODSSUPPLY_STATUS_DONE="5_PO_DONE";
	/**
	 * 忽略
	 */
	public static final String GOODSSUPPLY_STATUS_IGNORE="ignore";
	
	/**
	 * 日志类型－业务(仓储)
	 */
	public static final String BUSINESSLOG_TYPE_BUSS="2_BUSS";
	
	/**
	 * 质检台组名
	 */
	public static final String QC_STATION_POLICY = "QCStationPolicy";
	/**
	 * 打印机组名
	 */
	public static final String PRINTER_POLICY = "printerPolicy";
	
	public static final String PRINTER_INVOICE = "1_INVOICE_PRINTER";//发票
	public static final String PRINTER_RECEIPT = "2_RECEIPT_PRINTER";//收据
	public static final String PRINTER_TREATY = "3_TREATY_PRINTER"; //协议
	public static final String PRINTER_TRANSPORT = "4_TRANSPORT_PRINTER";//运单
	
	/**
	 * 产品的资源类型
	 */
	public static final String RESOURCE_TYPE_1= "001";//手机
	public static final String RESOURCE_TYPE_2= "002";//G3信息机
	public static final String RESOURCE_TYPE_3= "003";//套卡
	public static final String RESOURCE_TYPE_4= "004";//白卡
	public static final String RESOURCE_TYPE_5= "005";//其它
	public static final String RESOURCE_TYPE_6= "006";//G3号码
	
	//地市
	public static final String CITY_FS = "757";//佛山
	public static final String CITY_DG = "769";//东莞
	public static final String CITY_MM = "668";//茂名
	public static final String CITY_YF = "766";//云浮
	
	//手机品牌
	public static final String BRAND_TYPE_BrandTD = "BrandTD";
	public static final String BRAND_TYPE_BrandTD_NAME = "TD品牌";
	
	public static final String BRAND_TYPE_GOTONE = "BrandGotone";
	public static final String BRAND_TYPE_GOTONE_NAME = "全球通";
	
	public static final String BRAND_TYPE_BrandSzx = "BrandSzx";
	public static final String BRAND_TYPE_BrandSzx_NAME = "神州行";
	
	public static final String BRAND_TYPE_MZONE = "BrandMzone";
	public static final String BRAND_TYPE_MZONE_NAME = "动感地带";
	
	public static final String BRAND_TYPE_DZK = "BrandDzk";
	public static final String BRAND_TYPE_DZK_Name = "神州大众卡";
	
	//资源监控类型
	public static final String RES_MONITOR_TYPE = "resMonitorType";
	
	public static final String RES_MONITOR_IN_RET = "1_IN_RET";//收货
	public static final String RES_MONITOR_UP_PICK = "2_UP_PICK";//上架
	public static final String RES_MONITOR_DWON_PICK = "3_DWON_PICK";//下架
	public static final String RES_MONITOR_MOVE = "4_MOVE";//移位
	public static final String RES_MONITOR_OUT_PICK = "5_OUT_PICK";//发货
	public static final String RES_MONITOR_RETURN = "6_RETURN";//退货
	public static final String RES_MONITOR_TO_DAMAGE = "7_TO_DAMAGE";//资源报损
	public static final String RES_MONITOR_TO_GOOD = "8_TO_GOOD";//资源转正
	public static final String RES_MONITOR_TO_LOST = "9_TO_LOST";//资源报失
	public static final String RES_MONITOR_TO_RETURN = "10_TO_RETURN";//失物找回
	
	//出库类型
	public static final String OUT_TYPE = "outType";
	
	public static final String OUT_TYPE_IN_RET_OUT = "1_IN_RET_OUT";//进货退货出库
	public static final String OUT_TYPE_SALE_RET_OUT = "2_SALE_RET_OUT";//销退
	public static final String OUT_TYPE_ORDER_OUT = "3_ORDER_OUT";//订单出库
	public static final String OUT_TYPE_OTHER_OUT = "9_OTHER_OUT";//其他出库
	
	//支付方式
	public static final String PAYMETHOD = "paymethod";
	//渠道类型
	public static final String CHANNEL = "chanel";
	
	// 受理单回单差错类型
	public static final String ACCEPRET = "AccepRet";
	
	//单据类型
	public static final String PAPER_TYPE = "paperPolicy";
	
	public static final String PAPER_TYPE_1_INVOICE = "1_INVOICE";//发票
	public static final String PAPER_TYPE_2_RECEIPT = "2_RECEIPT";//收据
	public static final String PAPER_TYPE_3_TREATY = "3_TREATY";	//协议
	public static final String PAPER_TYPE_4_NORMAL_TRANSPORT = "4_NORMAL_TRANSPORT";//正常运单
	public static final String PAPER_TYPE_5_RECYCLING_TRANSPORT = "5_RECYCLING_TRANSPORT";//回收运单
	
	//发票打印时间配置
	public static final String INVOICE_PRINT_TIME = "invoicePrintTime";
	
	//发票打印模板配置
	public static final String INVOICE_TEMPLATE_POLICY = "invoiceTemplatePolicy";
	
	public static final String INVOICE_TEMPLATE_1_GENERAL = "1_GENERAL_INVOICE_TEMPLATE";//普通发票模板
	public static final String INVOICE_TEMPLATE_2_GENERAL_NEW = "2_GENERAL_NEW_INVOICE_TEMPLATE";//普通发票新模板
	public static final String INVOICE_TEMPLATE_3_FS = "3_FS_INVOICE_TEMPLATE";//佛山定制发票模板
	
	public static final String INVOICE_PRINT_TIME_1 = "1_PRD";//珠江三角洲
	public static final String INVOICE_PRINT_TIME_2 = "2_NOO_PRD";//非珠江三角洲
	
	//运单模板
	public static final String TRANSPORT_TEMPLATE = "transportTemplate";
	public static final String TRANSPORT_TEMPLATE_1_ARIP = "1_ARIP";//埃瑞普
	public static final String TRANSPORT_TEMPLATE_2_EMS = "2_EMS";//EMS
	/**
	 * 残损原因数据字典组名
	 */
	public static final String GOODTODAMAGE_CAUSE = "damageCause";
	
	/**
	 * 丢失原因数据字典组名
	 */
	public static final String RESLOST_CAUSE = "reslostCause";
	
	//商品类别
	public static final String OFFER_CLASS = "offerClass";
	
	public static final String OFFER_CLASS_0 = "0";//手机终端类
	public static final String OFFER_CLASS_1 = "1";//信息机类
	public static final String OFFER_CLASS_2 = "2";//套卡类
	public static final String OFFER_CLASS_3 = "3";//预存类
	public static final String OFFER_CLASS_4 = "4";//其他
	public static final String OFFER_CLASS_5 = "5";//数据卡
	
	public static final String TRANSPORT_PROC_MONIT = "transportProcMnoit";
	
	public static final String TRANSPORT_PROC_MONIT_01 = "1";
	public static final String TRANSPORT_PROC_MONIT_02 = "2";
	public static final String TRANSPORT_PROC_MONIT_03 = "3";
	//public static final String TRANSPORT_PROC_MONIT_04 = "4";
	
	// 通告类型
	public static final String SYSTEM_NOTICE_TYPE="sysNoticeType";
	
}
