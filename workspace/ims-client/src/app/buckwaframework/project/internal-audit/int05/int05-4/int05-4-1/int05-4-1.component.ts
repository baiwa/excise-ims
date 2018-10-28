import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "../../../../../common/helper";
import { MessageBarService, AjaxService } from "../../../../../common/services";
import { File } from "./../../../../../common/models/file";
import { Router, ActivatedRoute } from "@angular/router";
import { BreadCrumb } from "models/breadcrumb";

const URL = {
  SAVE_PcmList: "/ia/int0541/savePcmList",
  UPLOAD_Procurement: "/ia/int0541/upload",
  UPDATE_FIND_BY_ID: "/ia/int0541/findByid"
};

declare var $: any;
@Component({
  selector: "app-int05-4-1",
  templateUrl: "./int05-4-1.component.html",
  styleUrls: ["./int05-4-1.component.css"]
})
export class Int0541Component implements OnInit {
  breadcrumb: BreadCrumb[] = [];
  supplyChoice: string = "";
  numbers: string[] = [""];
  budgetType: string = "";
  budgetTypeList: any = [];
  budgetYear: any = null;
  projectName: string = "";
  projectCodeEgp: string = "";
  poNumber: any = null;
  tenderResults: any = 0;
  supplyType: any = null;
  file: File[];
  procurementList: any;
  flagValidate1_: any = null;
  flagValidate2_: any = null;
  flagValidate3_: any = null;
  flagValidate4_: any = null;
  flagValidate5_: any = null;
  flagValidate6_: any = null;
  flagValidate7_: any = null;
  flag: any = "";
  head: any = "";
  supplyChoiceList: string[];
  SupplyCategories: string[];
  dataEdit: any;
  procurementId: any;

  constructor(
    private ajax: AjaxService,
    private msg: MessageBarService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบพัสดุ", route: "#" },
      { label: "เพิ่มข้อมูลจัดซื้อจัดจ้าง", route: "#" }
    ];

    this.budgetTypeList = [
      "งบบุคลากร",
      "งบดำเนินงาน (โครงการ)",
      "งบดำเนินงาน (ขั้นต่ำ/ประจำ)",
      "งบลงทุน",
      "งบอุดหนุน",
      "งบรายจ่ายอื่น"
    ];

    this.supplyChoiceList = [
      "วิธีเฉพาะเจาะจง",
      "วิธีคัดเลือก",
      "วิธีสอบราคา",
      "วิธี E - Market",
      "วิธี E - Bidding"
    ];

    this.SupplyCategories = ["ซื้อ", "จ้าง", "เช่า"];

    this.flagValidate1_ = [];
    this.flagValidate2_ = [];
    this.flagValidate3_ = [];
    this.flagValidate4_ = [];
    this.flagValidate5_ = [];
    this.flagValidate6_ = [];
    this.flagValidate7_ = [];
  }

  ngOnInit() {
    //get params no link "/int05/4"
    this.flag = this.route.snapshot.queryParams["status"];
    this.head = this.route.snapshot.queryParams["head"];
    if (
      this.route.snapshot.queryParams["procurementId"] != null ||
      this.route.snapshot.queryParams["procurementId"] != undefined
    ) {
      //get params no link "/int05/4"
      this.procurementId = this.route.snapshot.queryParams["procurementId"];
      this.flag = this.route.snapshot.queryParams["status"];
      this.head = this.route.snapshot.queryParams["head"];

      this.ajax.post(
        URL.UPDATE_FIND_BY_ID,
        { procurementId: this.procurementId },
        res => {
          this.numbers = res.json();
          this.numbers.splice(0, 1);
          const data = res.json();
          for (let i = 1; i < data.length; i++) {
            setTimeout(() => {
              $("#procurementList" + (i - 1)).val(data[i].procurementList);
              $("#amount" + (i - 1)).val(data[i].amount);
              $("#unit" + (i - 1)).val(data[i].unit);
              $("#presetPrice" + (i - 1)).val(data[i].presetPrice);
              $("#appraisalPrice" + (i - 1)).val(data[i].appraisalPrice);
              $("#unitPrice" + (i - 1)).val(data[i].unitPrice);
              $("#price" + (i - 1)).val(data[i].price);
            }, 50);
          }

          $("#calendar_data").val(data[0].budgetYear);
          $("#projectName").val(data[0].projectName);
          $("#projectCodeEgp").val(data[0].projectCodeEgp);
          $("#poNumber").val(data[0].poNumber);
          setTimeout(() => {
            $("#budgetType").dropdown("set selected", data[0].budgetType);
            $("#supplyChoice").dropdown("set selected", data[0].supplyChoice);
          }, 50);
          this.tenderResults = data[0].tenderResults;
          this.supplyType = data[0].supplyType;
          setTimeout(() => {
            $(
              `input[name='jobDescription'][value='${data[0].jobDescription}']`
            ).prop("checked", true);
            $("#approveDatePlanData").val(data[0].approveDatePlan);
            $("#contractDatePlanData").val(data[0].contractDatePlan);
            $("#expireDatePlanData").val(data[0].expireDatePlan);
            $("#disbursementFinalPlanData").val(data[0].disbursementFinalPlan);
            $("#approveDateReportData").val(data[0].approveDateReport);
            $("#contractDateReportData").val(data[0].contractDateReport);
            $("#expireDateReportData").val(data[0].expireDateReport);
            $("#disbursementFinalReportData").val(
              data[0].disbursementFinalReport
            );
            $("#contractPartiesNum").val(data[0].contractPartiesNum);
            $("#contractPartiesName").val(data[0].contractPartiesName);
            $("#signedDatePlanData").val(data[0].signedDatePlan);
            $("#signedDateReport").val(data[0].signedDateReportData);
          }, 100);
        }
      );
    }

    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");

    $("#calendar").calendar({
      maxDate: new Date(),
      type: "year",
      text: TextDateTH,
      formatter: formatter("year"),
      onChange: (date, text, mode) => {
        this.budgetYear = text;
        // let _year = toDateLocale(date)[0].split("/")[2];
      }
    });
  }

  onChangeChoice = () => {
    setTimeout(() => {
      $(".ui.dropdown").dropdown();
      $(".ui.dropdown.ai").css("width", "100%");
      $("#approveDatePlan").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา")
      });

      $("#contractDatePlan").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา")
      });

      $("#expireDatePlan").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา")
      });

      $("#disbursementFinalPlan").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา")
      });

      $("#approveDateReport").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา")
      });

      $("#contractDateReport").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา")
      });

      $("#expireDateReport").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา")
      });

      $("#disbursementFinalReport").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา")
      });

      $("#signedDatePlan").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา")
      });

      $("#signedDateReport").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา")
      });
    }, 500);
  };

  onAddField = () => {
    this.numbers.push("");
  };

  onDelField = index => {
    if (this.numbers.length <= 1) {
      this.msg.errorModal("ไม่สามารถลบได้", "เกิดข้อผิดพลาด");
    } else {
      this.numbers.splice(index, 1);
    }
  };

  onSaveForm1 = e => {
    e.preventDefault();

    //send form data
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    formBody.append("procurementId", this.procurementId);
    formBody.append("budgetYear", $("#calendar_data").val());
    formBody.append("budgetType", this.budgetType);
    formBody.append("projectName", $("#projectName").val());
    formBody.append("projectCodeEgp", $("#projectCodeEgp").val());
    formBody.append("poNumber", $("#poNumber").val());
    formBody.append("supplyChoice", this.supplyChoice);
    formBody.append("tenderResults", this.tenderResults);
    let jobDescription = $("input[name='jobDescription']:checked").val();
    formBody.append(
      "jobDescription",
      jobDescription == undefined ? null : jobDescription
    );
    formBody.append("supplyType", this.supplyType);
    formBody.append(
      "approveDatePlan",
      (<HTMLInputElement>document.getElementById("approveDatePlanData")).value
    );
    formBody.append(
      "contractDatePlan",
      (<HTMLInputElement>document.getElementById("contractDatePlanData")).value
    );
    formBody.append(
      "expireDatePlan",
      (<HTMLInputElement>document.getElementById("expireDatePlanData")).value
    );
    formBody.append(
      "disbursementFinalPlan",
      (<HTMLInputElement>document.getElementById("disbursementFinalPlanData"))
        .value
    );
    formBody.append(
      "approveDateReport",
      (<HTMLInputElement>document.getElementById("approveDateReportData")).value
    );
    formBody.append(
      "contractDateReport",
      (<HTMLInputElement>document.getElementById("contractDateReportData"))
        .value
    );
    formBody.append(
      "expireDateReport",
      (<HTMLInputElement>document.getElementById("expireDateReportData")).value
    );
    formBody.append(
      "disbursementFinalReport",
      (<HTMLInputElement>document.getElementById("disbursementFinalReportData"))
        .value
    );
    formBody.append(
      "contractPartiesNum",
      (<HTMLInputElement>document.getElementById("contractPartiesNum")).value
    );
    formBody.append(
      "contractPartiesName",
      (<HTMLInputElement>document.getElementById("contractPartiesName")).value
    );

    //check flag save
    let chkFlg = "";
    for (let i = 0; i < this.numbers.length; i++) {
      if (
        (<HTMLInputElement>document.getElementById("procurementList" + i))
          .value != ""
      ) {
        this.flagValidate1_[i] = true;
      } else {
        this.flagValidate1_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("amount" + i)).value != ""
      ) {
        this.flagValidate2_[i] = true;
      } else {
        this.flagValidate2_[i] = false;
      }

      if ((<HTMLInputElement>document.getElementById("unit" + i)).value != "") {
        this.flagValidate3_[i] = true;
      } else {
        this.flagValidate3_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("presetPrice" + i)).value !=
        ""
      ) {
        this.flagValidate4_[i] = true;
      } else {
        this.flagValidate4_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("appraisalPrice" + i))
          .value != ""
      ) {
        this.flagValidate5_[i] = true;
      } else {
        this.flagValidate5_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("unitPrice" + i)).value != ""
      ) {
        this.flagValidate6_[i] = true;
      } else {
        this.flagValidate6_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("price" + i)).value != ""
      ) {
        this.flagValidate7_[i] = true;
      } else {
        this.flagValidate7_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("procurementList" + i))
          .value != "" &&
        (<HTMLInputElement>document.getElementById("unit" + i)).value != "" &&
        (<HTMLInputElement>document.getElementById("amount" + i)).value != "" &&
        (<HTMLInputElement>document.getElementById("presetPrice" + i)).value !=
          "" &&
        (<HTMLInputElement>document.getElementById("appraisalPrice" + i))
          .value != "" &&
        (<HTMLInputElement>document.getElementById("unitPrice" + i)).value !=
          "" &&
        (<HTMLInputElement>document.getElementById("price" + i)).value
      ) {
        chkFlg = "S";
      } else {
        chkFlg = "F";
      }
    }

    //check flag save
    if (chkFlg === "F") {
      this.msg.errorModal("กรุณากรอกข้อมูลให้ครบ");
    } else {
      this.ajax.upload(URL.UPLOAD_Procurement, formBody, res => {
        let data = res.json();
        let procurementId = data.data.procurementId;
        let procurementList = [];

        for (let i = 0; i < this.numbers.length; i++) {
          procurementList.push({
            procurementId: procurementId,
            procurementList: (<HTMLInputElement>(
              document.getElementById("procurementList" + i)
            )).value,
            amount: parseInt(
              (<HTMLInputElement>document.getElementById("amount" + i)).value
            ),
            unit: (<HTMLInputElement>document.getElementById("unit" + i)).value,
            presetPrice: parseInt(
              (<HTMLInputElement>document.getElementById("presetPrice" + i))
                .value
            ),
            appraisalPrice: parseInt(
              (<HTMLInputElement>document.getElementById("appraisalPrice" + i))
                .value
            ),
            unitPrice: parseInt(
              (<HTMLInputElement>document.getElementById("unitPrice" + i)).value
            ),
            price: parseInt(
              (<HTMLInputElement>document.getElementById("price" + i)).value
            )
          });
        }
        this.ajax.post(URL.SAVE_PcmList, procurementList, res => {
          const msg = res.json();
          if (msg.messageType == "C") {
            this.msg.successModal(msg.messageTh);
            this.router.navigate(["/int05/4"]);
          } else {
            this.msg.errorModal(msg.messageTh);
          }
        });
      });
    }
  };

  onSaveForm2 = e => {
    e.preventDefault();

    //send form data
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    formBody.append("budgetYear", $("#calendar_data").val());
    formBody.append("budgetType", this.budgetType);
    formBody.append("projectName", $("#projectName").val());
    formBody.append("projectCodeEgp", $("#projectCodeEgp").val());
    formBody.append("poNumber", $("#poNumber").val());
    formBody.append("supplyChoice", this.supplyChoice);
    formBody.append("tenderResults", this.tenderResults);
    let jobDescription = $("input[name='jobDescription']:checked").val();
    formBody.append(
      "jobDescription",
      jobDescription == undefined ? null : jobDescription
    );
    formBody.append("supplyType", this.supplyType);
    formBody.append(
      "approveDatePlan",
      (<HTMLInputElement>document.getElementById("approveDatePlanData")).value
    );
    formBody.append(
      "contractDatePlan",
      (<HTMLInputElement>document.getElementById("contractDatePlanData")).value
    );
    formBody.append(
      "expireDatePlan",
      (<HTMLInputElement>document.getElementById("expireDatePlanData")).value
    );
    formBody.append(
      "disbursementFinalPlan",
      (<HTMLInputElement>document.getElementById("disbursementFinalPlanData"))
        .value
    );
    formBody.append(
      "approveDateReport",
      (<HTMLInputElement>document.getElementById("approveDateReportData")).value
    );
    formBody.append(
      "contractDateReport",
      (<HTMLInputElement>document.getElementById("contractDateReportData"))
        .value
    );
    formBody.append(
      "expireDateReport",
      (<HTMLInputElement>document.getElementById("expireDateReportData")).value
    );
    formBody.append(
      "disbursementFinalReport",
      (<HTMLInputElement>document.getElementById("disbursementFinalReportData"))
        .value
    );
    formBody.append(
      "signedDatePlan",
      (<HTMLInputElement>document.getElementById("signedDatePlanData")).value
    );
    formBody.append(
      "signedDateReport",
      (<HTMLInputElement>document.getElementById("signedDateReportData")).value
    );
    formBody.append(
      "contractPartiesNum",
      (<HTMLInputElement>document.getElementById("contractPartiesNum")).value
    );
    formBody.append(
      "contractPartiesName",
      (<HTMLInputElement>document.getElementById("contractPartiesName")).value
    );

    let chkFlg = "";
    for (let i = 0; i < this.numbers.length; i++) {
      if (
        (<HTMLInputElement>document.getElementById("procurementList" + i))
          .value != ""
      ) {
        this.flagValidate1_[i] = true;
      } else {
        this.flagValidate1_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("amount" + i)).value != ""
      ) {
        this.flagValidate2_[i] = true;
      } else {
        this.flagValidate2_[i] = false;
      }

      if ((<HTMLInputElement>document.getElementById("unit" + i)).value != "") {
        this.flagValidate3_[i] = true;
      } else {
        this.flagValidate3_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("presetPrice" + i)).value !=
        ""
      ) {
        this.flagValidate4_[i] = true;
      } else {
        this.flagValidate4_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("appraisalPrice" + i))
          .value != ""
      ) {
        this.flagValidate5_[i] = true;
      } else {
        this.flagValidate5_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("unitPrice" + i)).value != ""
      ) {
        this.flagValidate6_[i] = true;
      } else {
        this.flagValidate6_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("price" + i)).value != ""
      ) {
        this.flagValidate7_[i] = true;
      } else {
        this.flagValidate7_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("procurementList" + i))
          .value != "" &&
        (<HTMLInputElement>document.getElementById("unit" + i)).value != "" &&
        (<HTMLInputElement>document.getElementById("amount" + i)).value != "" &&
        (<HTMLInputElement>document.getElementById("presetPrice" + i)).value !=
          "" &&
        (<HTMLInputElement>document.getElementById("appraisalPrice" + i))
          .value != "" &&
        (<HTMLInputElement>document.getElementById("unitPrice" + i)).value !=
          "" &&
        (<HTMLInputElement>document.getElementById("price" + i)).value
      ) {
        chkFlg = "S";
      } else {
        chkFlg = "F";
      }
    }
    if (chkFlg === "F") {
      this.msg.errorModal("กรุณากรอกข้อมูลให้ครบ", "เกิดข้อผิดพลาด");
    } else {
      this.ajax.upload(URL.UPLOAD_Procurement, formBody, res => {
        let data = res.json();
        let procurementId = data.data.procurementId;
        let procurementList = [];

        for (let i = 0; i < this.numbers.length; i++) {
          procurementList.push({
            procurementId: procurementId,
            procurementList: (<HTMLInputElement>(
              document.getElementById("procurementList" + i)
            )).value,
            amount: parseInt(
              (<HTMLInputElement>document.getElementById("amount" + i)).value
            ),
            unit: (<HTMLInputElement>document.getElementById("unit" + i)).value,
            presetPrice: parseInt(
              (<HTMLInputElement>document.getElementById("presetPrice" + i))
                .value
            ),
            appraisalPrice: parseInt(
              (<HTMLInputElement>document.getElementById("appraisalPrice" + i))
                .value
            ),
            unitPrice: parseInt(
              (<HTMLInputElement>document.getElementById("unitPrice" + i)).value
            ),
            price: parseInt(
              (<HTMLInputElement>document.getElementById("price" + i)).value
            )
          });
        }
        this.ajax.post(URL.SAVE_PcmList, procurementList, res => {
          const msg = res.json();
          if (msg.messageType == "C") {
            this.msg.successModal(msg.messageTh);
            this.router.navigate(["/int05/4"]);
          } else {
            this.msg.errorModal(msg.messageTh);
          }
        });
      });
    }
  };

  onSaveForm3 = e => {
    e.preventDefault();

    //send form data
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    formBody.append("budgetYear", $("#calendar_data").val());
    formBody.append("budgetType", this.budgetType);
    formBody.append("projectName", $("#projectName").val());
    formBody.append("projectCodeEgp", $("#projectCodeEgp").val());
    formBody.append("poNumber", $("#poNumber").val());
    formBody.append("supplyChoice", this.supplyChoice);
    formBody.append("tenderResults", this.tenderResults);
    let jobDescription = $("input[name='jobDescription']:checked").val();
    formBody.append(
      "jobDescription",
      jobDescription == undefined ? null : jobDescription
    );
    formBody.append("supplyType", this.supplyType);
    formBody.append(
      "approveDatePlan",
      (<HTMLInputElement>document.getElementById("approveDatePlanData")).value
    );
    formBody.append(
      "contractDatePlan",
      (<HTMLInputElement>document.getElementById("contractDatePlanData")).value
    );
    formBody.append(
      "expireDatePlan",
      (<HTMLInputElement>document.getElementById("expireDatePlanData")).value
    );
    formBody.append(
      "disbursementFinalPlan",
      (<HTMLInputElement>document.getElementById("disbursementFinalPlanData"))
        .value
    );
    formBody.append(
      "approveDateReport",
      (<HTMLInputElement>document.getElementById("approveDateReportData")).value
    );
    formBody.append(
      "contractDateReport",
      (<HTMLInputElement>document.getElementById("contractDateReportData"))
        .value
    );
    formBody.append(
      "expireDateReport",
      (<HTMLInputElement>document.getElementById("expireDateReportData")).value
    );
    formBody.append(
      "disbursementFinalReport",
      (<HTMLInputElement>document.getElementById("disbursementFinalReportData"))
        .value
    );
    formBody.append(
      "signedDatePlan",
      (<HTMLInputElement>document.getElementById("signedDatePlanData")).value
    );
    formBody.append(
      "signedDateReport",
      (<HTMLInputElement>document.getElementById("signedDateReportData")).value
    );
    formBody.append(
      "contractPartiesNum",
      (<HTMLInputElement>document.getElementById("contractPartiesNum")).value
    );
    formBody.append(
      "contractPartiesName",
      (<HTMLInputElement>document.getElementById("contractPartiesName")).value
    );

    let chkFlg = "";
    for (let i = 0; i < this.numbers.length; i++) {
      if (
        (<HTMLInputElement>document.getElementById("procurementList" + i))
          .value != ""
      ) {
        this.flagValidate1_[i] = true;
      } else {
        this.flagValidate1_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("amount" + i)).value != ""
      ) {
        this.flagValidate2_[i] = true;
      } else {
        this.flagValidate2_[i] = false;
      }

      if ((<HTMLInputElement>document.getElementById("unit" + i)).value != "") {
        this.flagValidate3_[i] = true;
      } else {
        this.flagValidate3_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("presetPrice" + i)).value !=
        ""
      ) {
        this.flagValidate4_[i] = true;
      } else {
        this.flagValidate4_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("appraisalPrice" + i))
          .value != ""
      ) {
        this.flagValidate5_[i] = true;
      } else {
        this.flagValidate5_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("unitPrice" + i)).value != ""
      ) {
        this.flagValidate6_[i] = true;
      } else {
        this.flagValidate6_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("price" + i)).value != ""
      ) {
        this.flagValidate7_[i] = true;
      } else {
        this.flagValidate7_[i] = false;
      }

      if (
        (<HTMLInputElement>document.getElementById("procurementList" + i))
          .value != "" &&
        (<HTMLInputElement>document.getElementById("unit" + i)).value != "" &&
        (<HTMLInputElement>document.getElementById("amount" + i)).value != "" &&
        (<HTMLInputElement>document.getElementById("presetPrice" + i)).value !=
          "" &&
        (<HTMLInputElement>document.getElementById("appraisalPrice" + i))
          .value != "" &&
        (<HTMLInputElement>document.getElementById("unitPrice" + i)).value !=
          "" &&
        (<HTMLInputElement>document.getElementById("price" + i)).value
      ) {
        chkFlg = "S";
      } else {
        chkFlg = "F";
      }
    }
    if (chkFlg === "F") {
      this.msg.errorModal("กรุณากรอกข้อมูลให้ครบ");
    } else {
      this.ajax.upload(URL.UPLOAD_Procurement, formBody, res => {
        let data = res.json();
        let procurementId = data.data.procurementId;
        let procurementList = [];

        for (let i = 0; i < this.numbers.length; i++) {
          procurementList.push({
            procurementId: procurementId,
            procurementList: (<HTMLInputElement>(
              document.getElementById("procurementList" + i)
            )).value,
            amount: parseInt(
              (<HTMLInputElement>document.getElementById("amount" + i)).value
            ),
            unit: (<HTMLInputElement>document.getElementById("unit" + i)).value,
            presetPrice: parseInt(
              (<HTMLInputElement>document.getElementById("presetPrice" + i))
                .value
            ),
            appraisalPrice: parseInt(
              (<HTMLInputElement>document.getElementById("appraisalPrice" + i))
                .value
            ),
            unitPrice: parseInt(
              (<HTMLInputElement>document.getElementById("unitPrice" + i)).value
            ),
            price: parseInt(
              (<HTMLInputElement>document.getElementById("price" + i)).value
            )
          });
        }
        this.ajax.post(URL.SAVE_PcmList, procurementList, res => {
          const msg = res.json();
          if (msg.messageType == "C") {
            this.msg.successModal(msg.messageTh);
            this.router.navigate(["/int05/4"]);
          } else {
            this.msg.errorModal(msg.messageTh);
          }
        });
      });
    }
  };

  onChangeUpload = (event: any) => {
    if (event.target.files && event.target.files.length > 0) {
      let reader = new FileReader();

      reader.onload = (e: any) => {
        const f = {
          name: event.target.files[0].name,
          type: event.target.files[0].type,
          value: e.target.result
        };
        this.file = [f];
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  };
}
