/**   
* @Package icloud.constants 
* @author sunyt 
* @date 2018年5月25日 下午2:27:39 
*/
package com.zhiyun.constants;

/**
 * @Package icloud.constants
 * @author sunyt
 * @date 2018年5月25日 下午2:27:39
 */
public class EnterpriseConstant {

	public enum Property {

		STATE_OWNED_ENTERPRISE(0, "国有企业"), FOREIGN_FUNDED_ENTERPRISE(1, "三资企业"), COLLECTIVE_ENTERPRISE(2,
				"集资企业"), PRIVATE_ENTERPRISE(3, "私营企业");

		private int id;

		private String desc;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		private Property(int id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public static String getPropertyDesc(int id) {
			for (Property property : Property.values()) {
				if (property.id == id)
					return property.desc;
			}
			return null;
		}

	}
	
	public enum CertificateType {

		IDENTITY_CARD(0, "身份证"), PASSPORT(1, "护照"), EXIT_ENTRY_PERMIT(2,
				"港澳台通行证");

		private int id;

		private String desc;

		private CertificateType(int id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public static String getCertificateTypeDesc(int id) {
			for (CertificateType certificateType : CertificateType.values()) {
				if (certificateType.id == id)
					return certificateType.desc;
			}
			return null;
		}

	}

	public enum Type {

		PERSONAL(0, "个人"), ENTERPRISE(1, "企业");

		private int id;

		private String desc;

		private Type(int id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getTypeDesc(int id) {
			for (Type type : Type.values()) {
				if (type.id == id)
					return type.desc;
			}
			return null;
		}
	}

	//经编行业 和加工类行业

	public enum Industry{

		INDUSTRY13("13","农副食品加工业"),
        INDUSTRY14("14","食品制造业"),
        INDUSTRY15("15","酒、饮料和精制茶制造业"),
        INDUSTRY16("16","烟草制品业"),
        INDUSTRY17("17","纺织业"),
        INDUSTRY18("18","纺织服装、服饰业"),
        INDUSTRY19("19","皮革、毛皮、羽毛及其制品和制鞋业"),
        INDUSTRY20("20","木材加工和木、竹、藤、棕、草制品业"),
        INDUSTRY21("21","家具制造业"),
        INDUSTRY22("22","造纸和纸制品业"),
        INDUSTRY23("23","印刷和记录媒介复制业"),
        INDUSTRY24("24","文教、工美、体育和娱乐用品制造业"),
        INDUSTRY25("25","石油、煤炭及其他燃料加工业"),
        INDUSTRY26("26","化学原料和化学制品制造业"),
        INDUSTRY27("27","医药制造业"),
        INDUSTRY28("28","化学纤维制造业"),
        INDUSTRY29("29","橡胶和塑料制品业"),
        INDUSTRY30("30","非金属矿物制品业"),
        INDUSTRY31("31","黑色金属冶炼和压延加工业"),
        INDUSTRY32("32","有色金属冶炼和压延加工业 "),
        INDUSTRY33("33","金属制品业"),
        INDUSTRY34("34","通用设备制造业"),
        INDUSTRY35("35","专用设备制造业"),
        INDUSTRY36("36","汽车制造业"),
        INDUSTRY37("37","铁路、船舶、航空航天和其他运输设备制造业"),
        INDUSTRY38("38","电气机械和器材制造业"),
        INDUSTRY39("39","计算机、通信和其他电子设备制造业"),
        INDUSTRY40("40","仪器仪表制造业"),
        INDUSTRY41("41","其他制造业"),
        INDUSTRY42("42","废弃资源综合利用业"),
        INDUSTRY43("43","金属制品、机械和设备修理业");



		public String id;

		public String label;

		private Industry(String id,String label){
			this.id = id;
			this.label = label;
		}

		public static String getIndustryDesc(String id) {
			for (Industry industry: Industry.values()) {
				if (industry.id.equals(id))
					return industry.label;
			}
			return null;
		}


	}

	public enum EnterpriseScale {

		LESS_THEN_TWENTY(0, 0, 20, "0~20人"), LESS_THEN_HUNDRED(1, 20, 100, "20~100人"), GREATER_THEN_HUNDRED(2, 100, -1,
				"大于100人");

		private int id;

		private int lowBound;

		private int upBound;

		private String desc;

		private EnterpriseScale(int id, int lowBound, int upBound, String desc) {
			this.id = id;
			this.lowBound = lowBound;
			this.upBound = upBound;
			this.desc = desc;
		}

		public static String getEnterpriseScaleDesc(int id) {
			for (EnterpriseScale enterpriseScale : EnterpriseScale.values()) {
				if (enterpriseScale.id == id) {
					return enterpriseScale.desc;
				}
			}
			return null;
		}

		public int getId() {
			return id;
		}

		public String getDesc() {
			return desc;
		}
	}

    public enum EmployeeScale {

        LESS_THEN_TWENTY(0, 0, 20, "0~20人"), LESS_THEN_HUNDRED(1, 20, 100, "20~100人"), GREATER_THEN_HUNDRED(2, 100, -1,
                "大于100人");

        private int id;

        private int lowBound;

        private int upBound;

        private String desc;

        private EmployeeScale(int id, int lowBound, int upBound, String desc) {
            this.id = id;
            this.lowBound = lowBound;
            this.upBound = upBound;
            this.desc = desc;
        }

        public static String getEmployeeScaleDesc(int id) {
            for (EmployeeScale employeeScale : EmployeeScale.values()) {
                if (employeeScale.id == id) {
                    return employeeScale.desc;
                }
            }
            return null;
        }

        public int getId() {
            return id;
        }

        public String getDesc() {
            return desc;
        }
    }

	public enum EquipmentScale {

		LESS_THEN_TWENTY(0, 0, 20, "0~20台"), LESS_THEN_FIFTY(1, 20, 50, "20~50台"), LESS_THEN_HUNDRED(2, 20, 100,
				"50~100台"), GREATER_THEN_HUNDRED(3, 100, -1, "大于100台");

		private int id;

		private int lowBound;

		private int upBound;

		private String desc;

		private EquipmentScale(int id, int lowBound, int upBound, String desc) {
			this.id = id;
			this.lowBound = lowBound;
			this.upBound = upBound;
			this.desc = desc;
		}

		public static String getEquipmentScaleDesc(int id) {
			for (EquipmentScale equipmentScale : EquipmentScale.values()) {
				if (id == equipmentScale.id) {
					return equipmentScale.desc;
				}
			}
			return null;
		}

	}

	public enum AnnualSales {

		LESS_THEN_HUNDRED(0, 0, 100, "100万以下"), LESS_THEN_THOUSAND(1, 100, 1000, "100万~1000万"), GREATER_THEN_THOUSAND(2,
				1000, -1, "1000万以上");

		private int id;

		private int lowBound;

		private int upBound;

		private String desc;

		private AnnualSales(int id, int lowBound, int upBound, String desc) {
			this.id = id;
			this.lowBound = lowBound;
			this.upBound = upBound;
			this.desc = desc;
		}

		public static String getAnnualSalesDesc(int id) {
			for (AnnualSales annualSales : AnnualSales.values()) {
				if (id == annualSales.id) {
					return annualSales.desc;
				}
			}
			return null;
		}

	}

	public enum BusinessType {

		Type0(0, "经营类型0"), Type1(1, "经营类型1"), Type2(2, "经营类型2"), Type3(3, "经营类型3");

		private int id;

		private String desc;

		private BusinessType(int id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public static String getBusinessType(int id) {
			for (BusinessType businessType : BusinessType.values()) {
				if (id == businessType.id) {
					return businessType.desc;
				}
			}
			return null;
		}

		public int getId() {
			return id;
		}

		public String getDesc() {
			return desc;
		}

	}

	public enum MarketClass {

		Type0(0, "产品大类0"), Type1(1, "产品大类1"), Type2(2, "产品大类2");

		private int id;

		private String desc;

		private MarketClass(int id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public static String getMarketClass(int id) {
			for (MarketClass marketClass : MarketClass.values()) {
				if (id == marketClass.id) {
					return marketClass.desc;
				}
			}
			return null;
		}

		public int getId() {
			return id;
		}

		public String getDesc() {
			return desc;
		}
	}

	public enum Market {

		Type1(1, "原料市场"), Type2(2, "成品市场"), Type3(3, "设备市场");

		private int id;

		private String desc;

		private Market(int id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public static String getMarket(int id) {
			for (Market market : Market.values()) {
				if (id == market.id) {
					return market.desc;
				}
			}
			return null;
		}

		public int getId() {
			return id;
		}

		public String getDesc() {
			return desc;
		}

	}

	public enum Province {
		BEIJING(1,"北京市","京"),  TIANJING(2,"天津市","津"),  HEBEI(3,"河北省","冀"),  SHANXI(4,"山西省","晋"),  NEIMENGGU(5,"内蒙古自治区","蒙"),  LIAONING(6,"辽宁省","辽"),  JILIN(7,"吉林省","吉"),  HEILONGJIANG(8,"黑龙江省","黑"),  SHANGHAI(9,"上海市","沪"),  JIANGSU(10,"江苏省","苏"),  ZHEJIANG(11,"浙江省","浙"),  ANHU(12,"安徽省","皖"),  FUJIAN(13,"福建省","闽"),  JIANGXI(14,"江西省","赣"),  SHANDONG(15,"山东省","鲁"),  HENAN(16,"河南省","豫"),  HUBEI(17,"湖北省","鄂"),  HUNAN(18,"湖南省","湘"),  GUANGDONG(19,"广东省","粤"),  GUANGXI(20,"广西壮族自治区","桂"),  HAINAN(21,"海南省","琼"),  CHONGQING(22,"重庆市","渝"),  SICHUAN(23,"四川省","川"),  GUIZHOU(24,"贵州省","黔"),  YUNAN(25,"云南省","滇"),  XIZANG(26,"西藏自治区","藏"),  SHANXI2(27,"陕西省","陕"),  GANSU(28,"甘肃省","甘"),  QINHAI(29,"青海省","青"),  NINGXIA(30,"宁夏回族自治区","宁"),  XINJIANG(31,"新疆维吾尔自治区","新"),  TAIWAN(32,"台湾省","台"),  GANGAO(33,"港澳","港澳");
		private int id;

		private String name;

		private String shortName;

		private Province(int id, String name, String shortName) {
			this.id = id;
			this.name = name;
			this.shortName = shortName;
		}

		public static String getProvince(int id) {
			for (Province province : Province.values()) {
				if (id == province.id) {
					return province.name;
				}
			}
			return null;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getShortName() {
			return shortName;
		}
	}
}
