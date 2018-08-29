import { Component, OnInit, AfterViewInit } from "@angular/core";
import {
  TravelCostHeader,
  TravelCostDetail,
  Contract
} from "../../../../../../common/models";
import { AjaxService, MessageBarService } from "../../../../../../common/services";
import { Prices } from "../../../../../../common/helper/travel";
import { Router, ActivatedRoute } from "@angular/router";
import {
  digit,
  numberWithCommas,
  TextDateTH,
  formatter,
  DecimalFormat
} from "../../../../../../common/helper";
import { TravelService } from "../../../../../../common/services/travel.service";
declare var $: any;
@Component({
  selector: "app-int09-1-1-1",
  templateUrl: "./int09-1-1-1.component.html",
  styleUrls: ["./int09-1-1-1.component.css"]
})
export class Int09111Component implements OnInit, AfterViewInit {
  status: string;
  id: number;
  headerId: number;
  view: boolean;

  sum: any;

  hdr: TravelCostHeader;
  detail: TravelCostDetail[];
  data: TravelCostDetail;

  typeDocs: string[];
  topics: string[][];
  topic: string[];

  selectDoc: string;
  selectTop: string;

  selectedDoc: string;
  selectedTop: string;
  sent: boolean;

  df: any;

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService,
    private travelService: TravelService
  ) {
    this.sum = {
      allowance: 0,
      rent: 0,
      travel: 0,
      etc: 0,
      total: 0
    };
    this.view = false;
    this.status = "create";
    this.hdr = new TravelCostHeader();
    this.detail = new Array<TravelCostDetail>();
    this.data = new TravelCostDetail();
    this.typeDocs = ["ทั่วไป", "วิชาการ", "อำนวยการ", "บริหาร"];
    this.topics = [
      ["ปฏิบัติงาน", "ชำนาญงาน", "อาวุโส", "ทักษะพิเศษ"],
      ["ปฏิบัติการ", "ชำนาญการ", "ชำนาญการพิเศษ", "เชี่ยวชาญ", "ทรงคุณวุฒิ"],
      ["ระดับต้น", "ระดับสูง"],
      ["ระดับต้น", "ระดับสูง"]
    ];
    this.topic = [];
    this.sent = false; // false
    this.selectedTop = ""; // ''
    this.hdr.startDate = null;
    this.hdr.endDate = null;
    this.df = new DecimalFormat("#,###.00");
    console.log(this.df.format(100000));
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.headerId = this.route.snapshot.queryParams["id"];
    if (this.headerId !== undefined) {
      this.view = true;
      const HEADER_URL = `ia/int09/lists/${this.headerId}`;
      this.ajax.get(HEADER_URL, res => {
        this.hdr = res.json()[0];
        this.detail = this.hdr.Detail;
        let date = new Date(this.hdr.startDate);
        this.hdr.startDate = `${digit(date.getDate())}/${digit(
          date.getMonth()
        )}/${digit(date.getFullYear() + 543)}`;
        date = new Date(this.hdr.endDate);
        this.hdr.endDate = `${digit(date.getDate())}/${digit(
          date.getMonth()
        )}/${digit(date.getFullYear() + 543)}`;
        // calculate all costs
        this.cal();
      });
    }
  }

  ngAfterViewInit() {
    $(".ui.radio.checkbox").checkbox();
    $("#example2").calendar({
      endCalendar: $("#example3"),
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: function(date) {
        if ($("#startDate").val() !== null) {
          var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
          var nd2 = $("#endDate")
            .val()
            .split("/");
          var st1 = [
            date.getDate(),
            date.getMonth() + 1,
            date.getFullYear() + 543
          ];
          var firstDate = new Date(st1[2], st1[1], st1[0]);
          var secondDate = new Date(nd2[2], nd2[1], nd2[0]);
          var rs = Math.round(
            Math.abs((firstDate.getTime() - secondDate.getTime()) / oneDay)
          );
          $("#allowanceDate").attr("max", rs);
          $("#rentDate").attr("max", rs);
        }
      }
    });
    $("#example3").calendar({
      startCalendar: $("#example2"),
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: function(date) {
        if ($("#startDate").val() !== null) {
          var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
          var st1 = $("#startDate")
            .val()
            .split("/");
          var nd2 = [
            date.getDate(),
            date.getMonth() + 1,
            date.getFullYear() + 543
          ];
          var firstDate = new Date(st1[2], st1[1], st1[0]);
          var secondDate = new Date(nd2[2], nd2[1], nd2[0]);
          var rs = Math.round(
            Math.abs((firstDate.getTime() - secondDate.getTime()) / oneDay)
          );
          $("#allowanceDate").attr("max", rs);
          $("#rentDate").attr("max", rs - 1);
        }
      }
    });
  }

  onSelectDoc = event => {
    this.topic = this.topics[event.target.value - 1];
    this.selectDoc = this.typeDocs[event.target.value - 1];
  };

  onSelectTop = event => {
    this.selectTop = this.topic[event.target.value - 1];
  };

  onSubmit = () => {
    // show form generate pdf
    this.sent = true;
    this.selectedTop = this.selectTop;
    this.hdr.startDate = $("#startDate").val();
    this.hdr.endDate = $("#endDate").val();
    const {
      workSheetHeaderName,
      departmentName,
      startDate,
      endDate,
      description
    } = this.hdr;
    let arr = startDate.split("/");
    const start = new Date();
    start.setDate(parseInt(arr[0]));
    start.setMonth(parseInt(arr[1]));
    start.setFullYear(parseInt(arr[2]) - 543);
    arr = endDate.split("/");
    const end = new Date();
    end.setDate(parseInt(arr[0]));
    end.setMonth(parseInt(arr[1]));
    end.setFullYear(parseInt(arr[2]) - 543);
    var data: TravelCostHeader = {
      workSheetHeaderId: 0,
      workSheetHeaderName: workSheetHeaderName,
      departmentName: departmentName,
      startDate: start,
      endDate: end,
      description: description,
      createdBy: "",
      createdDatetime: null,
      updateBy: "",
      updateDatetime: null,
      Detail: this.detail
    };
    const URL =
      this.headerId !== undefined ? "ia/int09/update/" : "ia/int09/create";
    var router = this.router;
    this.ajax.post(URL, data, function(res) {
      router.navigate(["/int09/1"]);
    });
  };

  maxDate(startDate, endDate) {
    var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
    var st1 = startDate.split("/");
    var nd2 = endDate.split("/");
    var firstDate = new Date(st1[2], st1[1], st1[0]);
    var secondDate = new Date(nd2[2], nd2[1], nd2[0]);

    return Math.round(
      Math.abs((firstDate.getTime() - secondDate.getTime()) / oneDay)
    );
  }

  getPrice(index, what) {
    const { category, degree, allowanceDate, rentDate } = this.detail[index];
    const num = what === "allowance" ? allowanceDate : rentDate;
    return Prices(category, degree, what) * num;
  }

  totalCost(index) {
    const { restType, travelCost, otherCost } = this.detail[index];
    let total: number = 0;
    total += parseFloat(this.getPrice(index, "allowance").toString());
    total += parseFloat(this.getPrice(index, restType).toString());
    total += parseFloat(travelCost.toString());
    total += parseFloat(otherCost.toString());
    return total;
  }

  selectedBox(e, index) {
    this.detail[index].checked = e.target.checked;
  }

  manageDate(e) {
    e.preventDefault();

    // extract constructs
    const {
      name,
      lastName,
      position,
      category,
      degree,
      allowanceDate,
      rentDate,
      restType,
      travelCost,
      otherCost,
      note
    } = e.target;

    // binding data
    const data: TravelCostDetail = new TravelCostDetail();
    data.name = name.value;
    data.lastName = lastName.value;
    data.position = position.value;
    data.category = parseInt(category.value) - 1;
    data.degree = parseInt(degree.value) - 1;
    data.allowanceDate = parseInt(allowanceDate.value);
    data.allowanceCost =
      Prices(data.category, data.degree, "allowance") *
      parseInt(allowanceDate.value);
    data.rentDate = parseInt(rentDate.value);
    data.rentCost =
      Prices(data.category, data.degree, restType.value) *
      parseInt(rentDate.value);
    data.restType = restType.value;
    data.travelCost = travelCost.value;
    data.otherCost = otherCost.value;
    data.note = note.value;
    data.sumCost =
      parseInt(data.allowanceCost.toString()) +
      parseInt(data.rentCost.toString()) +
      parseInt(data.travelCost.toString()) +
      parseInt(data.otherCost.toString());

    name.value = "";
    lastName.value = "";
    position.value = "";
    category.value = "";
    degree.value = "";
    allowanceDate.value = 0;
    rentDate.value = 0;
    restType.value = 0;
    travelCost.value = 0;
    otherCost.value = 0;
    note.value = "";

    if (this.status === "create") {
      this.detail.push(data);
      // calculate all costs
      this.cal();
      this.clearData();
    } else {
      this.detail[this.id] = data;
      this.status = "edit";
      // calculate all costs
      this.cal();
    }
  }

  deleteData() {
    this.msg.comfirm(
      boo => {
        if (boo) {
          // TODO
          this.detail = this.detail.filter(obj => {
            return !obj.checked;
          });
          // calculate all costs
          this.cal();
        }
      },
      "ต้องการลบข้อมูลที่เลือกทั้งหมดหรือไม่ ?",
      "การยืนยัน"
    );
  }

  editData(index: number) {
    // change `status` and `id`
    this.id = index;
    this.status = "edit";

    const data: any = this.detail[index];
    this.data = new TravelCostDetail();
    this.data = data;

    this.onSelectDoc({
      target: { value: parseInt(this.detail[index].category.toString()) + 1 }
    });
    this.onSelectTop({
      target: { value: parseInt(this.detail[index].degree.toString()) + 1 }
    });
  }

  clearData() {
    if (this.status === "create") {
      this.data = new TravelCostDetail();
    } else {
      this.data = new TravelCostDetail();
      this.status = "create";
    }
  }

  cal() {
    this.sum = {
      allowance: 0,
      rent: 0,
      travel: 0,
      etc: 0,
      total: 0
    };
    for (var i = 0; i < this.detail.length; i++) {
      this.sum.allowance += parseInt(this.detail[i].allowanceCost.toString());
      this.sum.rent += parseInt(this.detail[i].rentCost.toString());
      this.sum.travel += parseInt(this.detail[i].travelCost.toString());
      this.sum.etc += parseInt(this.detail[i].otherCost.toString());
      this.sum.total += parseInt(this.detail[i].sumCost.toString());
    }
  }

  checkedElements() {
    return (
      this.detail.findIndex(obj => {
        return obj.checked;
      }) == -1
    );
  }

  contractPage() {
    const promise = new Promise((resolve, reject) => {
      // new variable
      let allowance = 0;
      let rent = 0;
      let other = 0;
      let travel = 0;
      let sum = 0;
      // extract contruct
      // for summary costs
      this.detail.forEach(element => {
        allowance += parseFloat(element.allowanceCost.toString());
        rent += parseFloat(element.rentCost.toString());
        other += parseFloat(element.otherCost.toString());
        travel += parseFloat(element.travelCost.toString());
        sum += parseFloat(element.sumCost.toString());
      });
      // set data
      let body: Contract = {
        numberId: "",
        loanName: "",
        loanFrom: "",
        sendTo: "",
        locateAt: "",
        positionName: "",
        presentTo: "",
        sumCostTxt: "",
        reasonTxt: "",
        allowanceCost: allowance,
        otherCost: other,
        travelCost: travel,
        rentCost: rent,
        sumCost: sum,
        day: 1,
        dateFixed: null
      };
      // set data to service
      this.travelService.setTravelContract(body);
      //console.log(body);
      this.router.navigate(["int09/2"]);
    });
    // this.router.navigate(["int09/2"]);
  }
}
