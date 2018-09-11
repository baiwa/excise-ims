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

  idProcess: any;

  travelToHead1List: any;
  travelToHead2List: any;
  travelToHead3List: any;

  travelToHead: any;
  travelToHeadString: any;
  dateFromHead: any;
  dateToHead: any;

  totalFeedMoney:Number=0;
  totalRoostMoney: Number=0;
  totalPassage: Number=0;
  totalOtherExpenses: Number=0;
  totalTotalMoney: Number=0;

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService,
    private travelService: TravelService
  ) { }

  calenda = function () {
    let promise = new Promise((resolve, reject) => {
      $("#modalDate1").calendar('clear');
      $("#modalDate2").calendar('clear');
      $("#modalDate3").calendar('clear');
      $("#modalDate4").calendar('clear');
      $("#modalDate5").calendar('clear');
      $("#modalDate6").calendar('clear');
      setTimeout(() => resolve(true), 200);
    });
    promise.then(resolve => {
      if (resolve) {
        $("#modalDate1").calendar({
          endCalendar: $("#modalDate2"),
          type: "date",
          text: TextDateTH,
          formatter: formatter()
        });
        $("#modalDate2").calendar({
          startCalendar: $("#modalDate1"),
          type: "date",
          text: TextDateTH,
          formatter: formatter()
        });
        $("#modalDate3").calendar({
          endCalendar: $("#modalDate4"),
          type: "date",
          text: TextDateTH,
          formatter: formatter()
        });
        $("#modalDate4").calendar({
          startCalendar: $("#modalDate3"),
          type: "date",
          text: TextDateTH,
          formatter: formatter()
        });
        $("#modalDate5").calendar({
          endCalendar: $("#modalDate6"),
          type: "date",
          text: TextDateTH,
          formatter: formatter()
        });
        $("#modalDate6").calendar({
          startCalendar: $("#modalDate5"),
          type: "date",
          text: TextDateTH,
          formatter: formatter()
        });
      }
    });
  }

  dataTable = function () {
    var table = $('#tableData').DataTable({
      "lengthChange": true,
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,
      "ajax": {
        "url": '/ims-webapp/api/ia/int09111/list',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag": "TRUE",
            "idProcess": this.idProcess
          }));
        },
      },
      "columns": [
        {
          "data": "id",
          "render": function (data, type, row, meta) {
            return '<input type="checkbox">';
          },
          "className": "ui center aligned"
        }, {
          "data": "id",
          "className": "ui center aligned",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          "data": "name"
        }, {
          "data": "position"
        }, {
          "data": "feedDay",
          "className": "ui right aligned"
        }, {
          "data": "feedMoney",
          "className": "ui right aligned"
        }, {
          "data": "roostDay",
          "className": "ui right aligned"
        }, {
          "data": "roostMoney",
          "className": "ui right aligned"
        }, {
          "data": "passage",
          "className": "ui right aligned"
        }, {
          "data": "otherExpenses",
          "className": "ui right aligned"
        }, {
          "data": "totalMoney",
          "className": "ui right aligned"
        }, {
          "data": "remark"
        }, {
          "data": "id",
          "className": "ui center aligned",
          "render": function (data, type, row) {
            var btn = '';
            btn += '<button class="mini ui primary button btn-edit">แก้ไข</button>';
            return btn;
          }
        }
      ],
      "rowCallback": this.sum
    });

    //button edit>
    table.on('click', 'tbody tr button.btn-edit', () => {
      var closestRow = $(this).closest('tr');
      var data = table.row(closestRow).data();
      this.modalAdd();
      console.log(data);

    });
  }

  sum = ( row, data ) => {
    this.totalFeedMoney+=data.feedMoney;
    this.totalRoostMoney+=data.roostMoney;
    this.totalPassage+=data.passage;
    this.totalOtherExpenses+=data.otherExpenses;
    this.totalTotalMoney+=data.totalMoney;
  }
  modalAdd() {
    $('#modalAdd').modal('show');
    this.calenda();
  }

  saveData() {
    console.log("Save Data : True");
    $('modalAdd').modal('hide');
  }

  modalAddHead() {
    $('#modalAddHead').modal('show');
    this.calenda();
  }

  saveHead() {
    var DateH1 = $("#departureDateHead").val().split("/");
    var DateH2 = $("#returnDateHead").val().split("/");

    this.dateFromHead = DateH1[0] + " " + TextDateTH.months[parseInt(DateH1[1]) - 1] + " " + DateH1[2];
    this.dateToHead = DateH2[0] + " " + TextDateTH.months[parseInt(DateH2[1]) - 1] + " " + DateH2[2];

    console.log("Save Head : True");
    $('#modalAddHead').modal('hide');
  }


  clickCheckAll = event => {
    if (event.target.checked) {
      var node = $('#tableData').DataTable().rows().nodes();
      $.each(node, function (index, value) {
        $(this).find('input').prop('checked', true);
      });
    } else {
      var node = $('#tableData').DataTable().rows().nodes();
      $.each(node, function (index, value) {
        $(this).find('input').prop('checked', false);
      });
    }
  }

  travelToHead1Dropdown = () => {
    const URL = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL, { type: "SECTOR_VALUE" }, res => {
      this.travelToHead1List = res.json();
    });
  }

  travelToHead2Dropdown = e => {
    var id = e.target.value;
    if (id != "") {
      const URL = "combobox/controller/getDropByTypeAndParentId";
      this.ajax.post(URL, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelToHead2List = res.json();
        this.setTravelToHead(e);
      });
    }
  }

  travelToHead3Dropdown = e => {
    var id = e.target.value;
    if (id != "") {
      const URL = "combobox/controller/getDropByTypeAndParentId";
      this.ajax.post(URL, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelToHead3List = res.json();
        this.setTravelToHead(e);
      });
    }
  }



  setTravelToHead = e => {
    this.travelToHead = e.target.value;

    if ($("#travelToHead3").val() != "") {
      this.travelToHeadString = $('#travelToHead1 option:selected').text() + " " + $('#travelToHead2 option:selected').text() + " " + $('#travelToHead3 option:selected').text();
    } else if ($("#travelToHead2").val() != "") {
      this.travelToHeadString = $('#travelToHead1 option:selected').text() + " " + $('#travelToHead2 option:selected').text();
    } else if ($("#travelToHead1").val() != "") {
      this.travelToHeadString = $('#travelToHead1 option:selected').text();
    }

    console.log("travelToHeadString : ", this.travelToHeadString);
  }



  save() {

    $('#modalAddHead').modal('hide');
    const URL = "ia/int09111/save";
    this.ajax.post(URL, {
      idProcess: this.idProcess
    }, res => {
      const msg = res.json();

      if (msg.messageType == "C") {
        this.msg.successModal(msg.messageTh);
        this.clickBack();
      } else {
        this.msg.errorModal(msg.messageTh);
      }
    });
  }

  clickBack() {
    this.router.navigate(['/int09/1/1'], {
      queryParams: { idProcess: this.idProcess }
    });
  }


  ngOnInit() {
    this.idProcess = this.route.snapshot.queryParams["idProcess"];
    console.log("idProcess : ", this.idProcess);
    this.dataTable();
    this.calenda();
    this.travelToHead1Dropdown();

  }


  ngAfterViewInit() {

  }



}
