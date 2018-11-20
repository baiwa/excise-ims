import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { ActivatedRoute } from '@angular/router';
import { AjaxService } from 'services/ajax.service';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { AuthService } from 'services/auth.service';
import { Utils } from "helpers/utils";


declare var $: any;
const URL = {
  LIST: AjaxService.CONTEXT_PATH + "ia/int0806/list"
};

@Component({
  selector: 'app-int08-7',
  templateUrl: './int08-7.component.html',
  styleUrls: ['./int08-7.component.css']
})
export class Int087Component implements OnInit {
  @Output() discard = new EventEmitter<any>();
 
  breadcrumb: BreadCrumb[];
  obj: data;
  box1: Boolean;
  box2: Boolean;
  table2:any;
  searchData:any;
 
  constructor( 
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private authService: AuthService,
    ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบรายได้", route: "#" },
      { label: "รายงานผลการตรวจสอบ", route: "#" },
    ];

    this.searchData = {
      startDate: "",
      endDate: "",
      account: "",
      combo1: "",
      combo2: "",
      flag: ""
    };

    this.obj = new data();
   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-087').then(user=>{
    //this.user= user;

    if (user.fullName) {
      this.obj.officer = user.fullName;
    }else{
      this.obj.officer = "";
    }

    if(user.title){
      this.obj.position=user.title;
    }else{
      this.obj.position="";
    }

   
    });
    this.calenda();
    this.dataTable();
    this.dataTable2();
  }

  calenda = () => {
    $("#date1").calendar({
      endCalendar: $("#date1"),
      type: "date",
      text: TextDateTH,
      formatter: formatter('วดป'),
      onChanges: (date , text)=>{
       $("#date").val(text);
      }
    });

  }
  dataTable=()=>{
    if ($('#tableData').DataTable() != null) {$('#tableData').DataTable().destroy();};
    var table = $('#tableData').DataTableTh({
      "lengthChange":false,
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,    

      "ajax" : {
        "url" : '/ims-webapp/api/ia/int085/list',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : $("#searchFlag").val(),
            "billLost"  : $("#billLost").val()
            
          }));
        },  
      },
      "columns": [
        {
          "data": "officeCode",
          "render": function (data, type, row, meta) {
              return meta.row + meta.settings._iDisplayStart + 1;
          },
          "className": "ui center aligned"
        }, {
          "data": "officeName"
        }, {
          "data": "startDate","className":"center"
        }, {
          "data": "endDate","className":"center"
        }, {
          "data": "billAll","className":"right"
        }, {
          "data": "billWaste","className":"right"
        }, {
          "data": "riskRemark"
        }
      ]
    });
  }
  dataTable2=()=> {
    if (this.table2 != null || this.table2 != undefined) {
      this.table2.destroy();
    }

    //render check number is null or empty
    let renderNumber = function(data, type, row, meta) {
      return Utils.isNull($.trim(data))
        ? "-"
        : $.fn.dataTable.render.number(",", ".", 2, "").display(data);
    };

    //render check string is null or empty
    let renderString = function(data, type, row, meta) {
      if (Utils.isNull(data)) {
        data = "-";
      }
      return data;
    };

    this.table2 = $("#dataTable2").DataTableTh({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      ajax: {
        type: "POST",
        url: URL.LIST,
        data : this.searchData
      },
      columns: [
        {
          render: function(data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        },
        {
          data: "receiptNo",
          render: renderString
        },
        {
          data: "trnDateStr",
          render: renderString
        },
        {
          data: "depositDateStr",
          render: renderString
        },
        {
          data: "nettaxAmount",
          render: renderNumber
        },
        {
          data: "netlocAmount",
          render: renderNumber
        },
        {
          render: function(data, type, full, meta) {
            let icon = "-";
            if (full.statusDate === "S") {
              icon = `<i class="check icon" style="color:green"> </i>`;
            } else if (full.statusDate === "F") {
              icon = `<i class="close icon" style="color:red"> </i>`;
            }
            return icon;
          }
        },
        {
          render: function(data, type, full, meta) {
            let icon = "-";
            if (full.statusMoney === "S") {
              icon = `<i class="check icon" style="color:green"> </i>`;
            } else if (full.statusMoney === "F") {
              icon = `<i class="close icon" style="color:red"> </i>`;
            }
            return icon;
          }
        }
      ],
      columnDefs: [
        {
          targets: [0, 1, 2, 3, 6, 7],
          className: "center"
        },
        {
          targets: [4, 5],
          className: "right"
        }
      ],

      rowCallback: (row, data, index) => {
        console.log(data);
      }
    });
  }


  onSubmit = e => {
    this.obj.title="รายงานผลการตรวจสอบรายได้"
    if(this.box1 && !this.box2){
      console.log("1,!2")
      this.obj.group1Flag="Y"
      this.obj.group2Flag="N"
    }else if(this.box2 && !this.box1){
      console.log("!1,2")
      this.obj.group1Flag="N"
      this.obj.group2Flag="Y"
    }else if(this.box1 && this.box2){
      console.log("1,2")
      this.obj.group1Flag="Y"
      this.obj.group2Flag="Y"
    }else{
      console.log("!1,!2")
      this.obj.group1Flag="N"
      this.obj.group2Flag="N"
    }


    console.log(this.obj);
    this.obj.date =   $("#date").val();
    var form = document.createElement("form");
		form.method = "POST";
		form.action = AjaxService.CONTEXT_PATH + "internalAudit/report/pdf/int/reportCheckIncome";
    form.style.display = "none";
    form.target = "_blank"       
    var jsonInput = document.createElement("input");
    jsonInput.name = "json";
    jsonInput.value = JSON.stringify(this.obj);
    form.appendChild(jsonInput);

    document.body.appendChild(form);
	  form.submit();


  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };

}

class data {
  logo: string = "logo1.jpg";
  title:string ;
  government : string;
  date : string;   
  study : string;
  billLost: string;
  sendMoney: string;
  group1Flag: string;
  group2Flag: string;
  officer:string;
  position:string;
  documentNo:string;
} 

