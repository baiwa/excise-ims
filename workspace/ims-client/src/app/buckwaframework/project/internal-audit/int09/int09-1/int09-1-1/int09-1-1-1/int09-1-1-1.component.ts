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

  btnModal: any;
  ifallowance: any='';
  ifroost: any='';

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
   
  typeList: any;
  gradeList: any;
  // departureList: any;
  trainingList: any;
  allowanceList: any;
  roostList: any;
  trainingTypeList: any;
  roomTypeList: any;

  departureFrom:any;
  departureTo:any;

  head:any;

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService,
    private travelService: TravelService
  ) { }

  ngOnInit() {
    // $('.ui.dropdown').dropdown();
    this.idProcess = this.route.snapshot.queryParams["idProcess"];
    console.log("idProcess : ", this.idProcess);
    this.dataTable();
    this.calenda();
    this.getHead();
    this.travelToHead1Dropdown();
    this.typeDropdown();
    // this.departureDropdown();
    this.allowanceDropdown();
    this.trainingDropdown();
    this.roostDropdown();
    this.trainingTypeDropdown();
    this.roomTypeDropdown();

  }


  ngAfterViewInit() {

  }
  getHead = () =>{
  
    const URL = "ia/int0911/gethead";
    this.ajax.post(URL, { idProcess: this.idProcess}, res => {
      this.head = res.json();
      console.log("Head : ",this.head);

      // this.pickedTypeH = (this.head.pickedType==1162)?'ก่อนเดินทาง':'หลังเดินทาง';
      // this.fiscalYearH = this.head.fiscalYear;
      this.dateFromHead = parseInt(this.head.departureDate.split("/")[0])+" "+TextDateTH.months[parseInt(this.head.departureDate.split("/")[1]) - 1]+" "+this.head.departureDate.split("/")[2];
      this.dateToHead = parseInt(this.head.returnDate.split("/")[0])+" "+TextDateTH.months[parseInt(this.head.returnDate.split("/")[1]) - 1]+" "+this.head.returnDate.split("/")[2];
      this.travelToHeadString = this.head.travelToDescription;
    });
  }

  getDepartureFrom = (e) =>{
  console.log("getDepartureFrom : ",e.target.value);
  this.departureFrom=e.target.value;
  
  }

  getDepartureTo = (e) =>{
    console.log("getDepartureTo : ",e.target.value);
    this.departureTo=e.target.value;
    }

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
          type: "date",
          text: TextDateTH,
          formatter: formatter()
        });
        $("#modalDate4").calendar({
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

  dataTable = () =>{
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
          "className": "ui right aligned",
          "render": $.fn.dataTable.render.number(',', '.', 2, '')
        }, {
          "data": "roostDay",
          "className": "ui right aligned"
        }, {
          "data": "roostMoney",
          "className": "ui right aligned",
          "render": $.fn.dataTable.render.number(',', '.', 2, '')
        }, {
          "data": "passage",
          "className": "ui right aligned",
          "render": $.fn.dataTable.render.number(',', '.', 2, '')
        }, {
          "data": "otherExpenses",
          "className": "ui right aligned",
          "render": $.fn.dataTable.render.number(',', '.', 2, '')
        }, {
          "data": "totalMoney",
          "className": "ui right aligned",
          "render": $.fn.dataTable.render.number(',', '.', 2, '')
        }, {
          "data": "remark"
        }, {
          "data": "id",
          "className": "ui center aligned",
          "render": function (data, type, row) {
            var btn = '';
            btn += '<button class="mini ui primary button btn-edit">แก้ไข</button>';
            btn +='<button class="mini ui red button btn-delete">ลบ</button>';
            return btn;
          }
        }
      ],
      "rowCallback": this.sum
    });

    //button edit>
    table.on('click', 'tbody tr button.btn-edit',(e)=> {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
      this.modalEdit(data);
    });
    table.on('click', 'tbody tr button.btn-delete', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
     
      const URL = "ia/int09111/delete";
      this.msg.comfirm((res) => {
        if(res){
      
      this.ajax.post(URL, {id:data.id}, res => {        
        const msg = res.json();
      if (msg.messageType == "C") {
        this.msg.successModal(msg.messageTh);
      } else {
        this.msg.errorModal(msg.messageTh);
      }
      $("#searchFlag").val("TRUE");
      this.setSum0();
      $('#tableData').DataTable().ajax.reload();
      });
     }
    },"ลบรายการ");
      
    });
  }

  sum = ( row, data ) => {
    console.log("data : ",data);
    this.totalFeedMoney+=data.feedMoney;
    this.totalRoostMoney+=data.roostMoney;
    this.totalPassage+=data.passage;
    this.totalOtherExpenses+=data.otherExpenses;
    this.totalTotalMoney+=data.totalMoney;
  }

  df(what): string {
    const df = new DecimalFormat("###,###.00");
    return df.format(what);
  }

  setSum0 = () =>{
    this.totalFeedMoney=0;
    this.totalRoostMoney=0;
    this.totalPassage=0;
    this.totalOtherExpenses=0;
    this.totalTotalMoney=0;
  }


  typeDropdown = () =>{
  
    const URL = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL, { type: "ACC_FEE",lovIdMaster: 306}, res => {
      this.typeList = res.json();
    });
  }
  
  gradeDropdown = e =>{
    $("#grade").dropdown("restore defaults");
    var id = e.target.value;
    console.log("id gradeDropdown : ",id);
      if (id != "") {
      const URL = "combobox/controller/getDropByTypeAndParentId";
      this.ajax.post(URL, { type: "ACC_FEE",lovIdMaster: id}, res => {
        this.gradeList = res.json();
      });
    }
  }
  // departureDropdown = () =>{
  //   const URL = "combobox/controller/getDropByTypeAndParentId";
  //   this.ajax.post(URL, { type: "ACC_FEE",lovIdMaster: 1178}, res => {
  //     this.departureList = res.json();
  //   });
  // }
  
  allowanceDropdown = () =>{
    const URL = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL, { type: "ACC_FEE",lovIdMaster: 1182}, res => {
      this.allowanceList = res.json();
    });
  }
  ngIfallowance=e=>{
  this.ifallowance=e.target.value;
  
  }

  trainingDropdown = () =>{
    const URL = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL, { type: "ACC_FEE",lovIdMaster: 336}, res => {
      this.trainingList = res.json();
    });
  }

  roostDropdown = () =>{
    const URL = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL, { type: "ACC_FEE",lovIdMaster: 1185}, res => {
      this.roostList = res.json();
    });
  }
  ngIfroost=e=>{
    this.ifroost=e.target.value;
    
    }

  trainingTypeDropdown = () =>{
    const URL = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL, { type: "ACC_FEE",lovIdMaster: 1189}, res => {
      this.trainingTypeList = res.json();
    });
  }
  roomTypeDropdown = () =>{
    const URL = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL, { type: "ACC_FEE",lovIdMaster: 1190}, res => {
      this.roomTypeList = res.json();
    });
  }

  modalAdd() {
    this.btnModal = 'S';
    $('#modalAdd').modal({
      onShow: ()=>{
        this.calenda();
        $('.ui.radio.checkbox').checkbox('set unchecked');

        $("#type").dropdown("restore defaults");
        $("#grade").dropdown("restore defaults");
        $("#training").dropdown("restore defaults");
        $("#allowance").dropdown("restore defaults");
        $("#roost").dropdown("restore defaults");
        $("#trainingType").dropdown("restore defaults");
        $("#roomType").dropdown("restore defaults");
        
        $('input[type=text]').val("");
        $('input[type=number]').val("");
        $('#remarkT').val("");
      }
    }).modal('show');
    
  }
  modalEdit=(data)=> {
    console.log("data edit : ",data.int09FormDtlVo);
   

    this.btnModal = 'E';
    $('#modalAdd').modal({
      onShow: ()=>{
         this.calenda();
         $("#id").val(data.int09FormDtlVo.id);
         $("#idDtl").val(data.id);
         $("#name").val(data.int09FormDtlVo.name);
         $("#lastName").val(data.int09FormDtlVo.lastName);
         $("#position").val(data.int09FormDtlVo.position);
       
         $("#type").dropdown('set selected',data.int09FormDtlVo.type);
         setTimeout(() => {
          $("#grade").dropdown('set selected',data.int09FormDtlVo.grade);
         }, 500);
      

         $("#permissionDate").val(data.int09FormDtlVo.permissionDate);
         $("#writeDate").val(data.int09FormDtlVo.writeDate);

         
         this.departureFrom=data.int09FormDtlVo.departureFrom;
          if(this.departureFrom==1179){
            $('.ui.radio.checkbox.departureFrom1').checkbox('set checked');
          }else if(this.departureFrom==1180){
            $('.ui.radio.checkbox.departureFrom2').checkbox('set checked');
          }else if(this.departureFrom==1181){
            $('.ui.radio.checkbox.departureFrom3').checkbox('set checked');
          }

         this.departureTo=data.int09FormDtlVo.departureTo;
          if(this.departureTo==1179){
            $('.ui.radio.checkbox.departureTo1').checkbox('set checked');
          }else if(this.departureTo==1180){
            $('.ui.radio.checkbox.departureTo2').checkbox('set checked');
          }else if(this.departureTo==1181){
            $('.ui.radio.checkbox.departureTo3').checkbox('set checked');
          }
        
       
        //  $("#departure").dropdown('set selected',data.int09FormDtlVo.departure);

         $("#departureDate").val(data.int09FormDtlVo.departureDate);
         $("#returnDate").val(data.int09FormDtlVo.returnDate);

         $("#allowance").dropdown('set selected',data.int09FormDtlVo.allowance);
         $("#training").dropdown('set selected',data.int09FormDtlVo.training);
         $("#roost").dropdown('set selected',data.int09FormDtlVo.roost);
         $("#trainingType").dropdown('set selected',data.int09FormDtlVo.trainingType);
         $("#roomType").dropdown('set selected',data.int09FormDtlVo.roomType);

         $("#numberDate").val(data.int09FormDtlVo.numberDate);
         $("#passage").val(data.int09FormDtlVo.passage);
         $("#otherExpenses").val(data.int09FormDtlVo.otherExpenses);
         $("#remarkT").val(data.int09FormDtlVo.remark);
      }
    }).modal('show');
   
  }

  saveData() {
    console.log("Save");
    $('modalAdd').modal('hide');
    const URL = "ia/int09111/save";
    this.ajax.post(URL, { int09FormDtlVo:{
      idProcess: this.idProcess,
      name:$("#name").val(),
      lastName:$("#lastName").val(),
      position:$("#position").val(),
      type:$("#type").val(),
      grade:$("#grade").val(),
      permissionDate:$("#permissionDate").val(),
      writeDate:$("#writeDate").val(),
      departureFrom:this.departureFrom,
      departureTo:this.departureTo,
      departureDate:$("#departureDate").val(),
      returnDate:$("#returnDate").val(),
      allowance:$("#allowance").val(),
      training:$("#training").val(),
      roost:$("#roost").val(),
      trainingType:$("#trainingType").val(),
      roomType:$("#roomType").val(),
      numberDate:$("#numberDate").val(),
      passage:$("#passage").val(),
      otherExpenses:$("#otherExpenses").val(),
      remark:$("#remarkT").val()}
    },res => {
      const commonMessage = res.json();
      
    if (commonMessage.msg.messageType == "C") {
      this.msg.successModal(commonMessage.msg.messageTh);
      console.log("commonMessage.data : ",commonMessage.data);
    } else {
      this.msg.errorModal(commonMessage.msg.messageTh);
    }
    $("#searchFlag").val("TRUE");
    this.setSum0();
    $('#tableData').DataTable().ajax.reload();
    });
  }
  editData() {
    console.log("Edit");
    $('modalAdd').modal('hide');
    const URL = "ia/int09111/edit";
    this.ajax.post(URL, { id:$("#idDtl").val(),int09FormDtlVo:{
      id:$("#id").val(),
      idProcess: this.idProcess,
      name:$("#name").val(),
      lastName:$("#lastName").val(),
      position:$("#position").val(),
      type:$("#type").val(),
      grade:$("#grade").val(),
      permissionDate:$("#permissionDate").val(),
      writeDate:$("#writeDate").val(),
      departureFrom:this.departureFrom,
      departureTo:this.departureTo,
      departureDate:$("#departureDate").val(),
      returnDate:$("#returnDate").val(),
      allowance:$("#allowance").val(),
      training:$("#training").val(),
      roost:$("#roost").val(),
      trainingType:$("#trainingType").val(),
      roomType:$("#roomType").val(),
      numberDate:$("#numberDate").val(),
      passage:$("#passage").val(),
      otherExpenses:$("#otherExpenses").val(),
      remark:$("#remarkT").val()}
    },res => {
      const commonMessage = res.json();
      
    if (commonMessage.msg.messageType == "C") {
      this.msg.successModal(commonMessage.msg.messageTh);
      console.log("commonMessage.data : ",commonMessage.data);
    } else {
      this.msg.errorModal(commonMessage.msg.messageTh);
    }
    $("#searchFlag").val("TRUE");
    this.setSum0();
    $('#tableData').DataTable().ajax.reload();
    });
  }

  modalAddHead() {
    $('#modalAddHead').modal('show');
    this.calenda();
  }

  // saveHead() {
  //   var DateH1 = $("#departureDateHead").val().split("/");
  //   var DateH2 = $("#returnDateHead").val().split("/");

  //   this.dateFromHead = DateH1[0] + " " + TextDateTH.months[parseInt(DateH1[1]) - 1] + " " + DateH1[2];
  //   this.dateToHead = DateH2[0] + " " + TextDateTH.months[parseInt(DateH2[1]) - 1] + " " + DateH2[2];
  //   $('#modalAddHead').modal('hide');
  // }




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
    const URL = "ia/int09111/saveAll";
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


 



}
