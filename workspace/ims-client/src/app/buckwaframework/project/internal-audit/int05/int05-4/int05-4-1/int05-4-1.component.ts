import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "../../../../../common/helper";
import { MessageBarService, AjaxService } from "../../../../../common/services";
import { File } from "./../../../../../common/models/file";

const URL = {
  SAVE_PcmList: "/ia/int0541/savePcmList",
  UPLOAD_Procurement: "/ia/int0541/upload"
};

declare var $: any;
@Component({
  selector: "app-int05-4-1",
  templateUrl: "./int05-4-1.component.html",
  styleUrls: ["./int05-4-1.component.css"]
})
export class Int0541Component implements OnInit {
  supplyChoice: string = "";
  numbers: string[] = [""];
  budgetType: string = "";
  budgetTypeList: any = [];
  budgetYear: any = null;
  projectName: string = "";
  approveDatePlan: any = null;
  contractDatePlan: any = null;
  expireDatePlan: any = null;
  disbursementFinalPlan: any = null;
  approveDateReport: any = null;
  contractDateReport: any = null;
  expireDateReport: any = null;
  disbursementFinalReport: any = null;
  projectCodeEgp: string = "";
  poNumber: any = null;
  tenderResults: any = 0;
  supplyType: any = null;
  signedDatePlan: any = null;
  signedDateReport: any = null;
  file: File[];
  procurementList: any;

  constructor(private ajax: AjaxService, private msg: MessageBarService) {
    this.budgetTypeList = [
      "งบบุคลากร",
      "งบดำเนินงาน (โครงการ)",
      "งบดำเนินงาน (ขั้นต่ำ/ประจำ)",
      "งบลงทุน",
      "งบอุดหนุน",
      "งบรายจ่ายอื่น"
    ];
  }

  ngOnInit() {
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
    // this.loadCalendar();
    setTimeout(() => {
      $("#approveDatePlan").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา"),
        onChange: (date, text, mode) => {
          this.approveDatePlan = text;
        }
      });

      $("#contractDatePlan").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา"),
        onChange: (date, text, mode) => {
          this.contractDatePlan = text;
        }
      });

      $("#expireDatePlan").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา"),
        onChange: (date, text, mode) => {
          this.expireDatePlan = text;
        }
      });

      $("#disbursementFinalPlan").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา"),
        onChange: (date, text, mode) => {
          this.disbursementFinalPlan = text;
        }
      });

      $("#approveDateReport").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา"),
        onChange: (date, text, mode) => {
          this.approveDateReport = text;
        }
      });

      $("#contractDateReport").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา"),
        onChange: (date, text, mode) => {
          this.contractDateReport = text;
        }
      });

      $("#expireDateReport").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา"),
        onChange: (date, text, mode) => {
          this.expireDateReport = text;
        }
      });

      $("#disbursementFinalReport").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา"),
        onChange: (date, text, mode) => {
          this.disbursementFinalReport = text;
        }
      });

      $("#signedDatePlan").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา"),
        onChange: (date, text, mode) => {
          this.signedDatePlan = text;
        }
      });

      $("#signedDateReport").calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา"),
        onChange: (date, text, mode) => {
          this.signedDateReport = text;
        }
      });
    }, 500);
  };

  // loadCalendar = () => {};

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
    formBody.append("budgetYear", this.budgetYear);
    formBody.append("budgetType", this.budgetType);
    formBody.append("projectName", this.projectName);
    formBody.append("projectCodeEgp", this.projectCodeEgp);
    formBody.append("poNumber", this.poNumber);
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

    let chkFlg = "";
    for (let i = 0; i < this.numbers.length; i++) {
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
        console.log(chkFlg);
      }
    }
    if (chkFlg === "F") {
      this.msg.errorModal("กรุณากรอกข้อมูลให้ครบ", "เกิดข้อผิดพลาด");
    } else {
      this.ajax.upload(URL.UPLOAD_Procurement, formBody, res => {
        let data = res.json();
        console.log(data.data.procurementId);
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
        console.log(procurementList);
        this.ajax.post(URL.SAVE_PcmList, procurementList, res => {
          const msg = res.json();
          if (msg.messageType == "C") {
            this.msg.successModal(msg.messageTh);
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
    formBody.append("budgetYear", this.budgetYear);
    formBody.append("budgetType", this.budgetType);
    formBody.append("projectName", this.projectName);
    formBody.append("projectCodeEgp", this.projectCodeEgp);
    formBody.append("poNumber", this.poNumber);
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
        console.log(data.data.procurementId);
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
        console.log(procurementList);
        this.ajax.post(URL.SAVE_PcmList, procurementList, res => {
          const msg = res.json();
          if (msg.messageType == "C") {
            this.msg.successModal(msg.messageTh);
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
    formBody.append("budgetYear", this.budgetYear);
    formBody.append("budgetType", this.budgetType);
    formBody.append("projectName", this.projectName);
    formBody.append("projectCodeEgp", this.projectCodeEgp);
    formBody.append("poNumber", this.poNumber);
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
        console.log(data.data.procurementId);
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
        console.log(procurementList);
        this.ajax.post(URL.SAVE_PcmList, procurementList, res => {
          const msg = res.json();
          if (msg.messageType == "C") {
            this.msg.successModal(msg.messageTh);
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
