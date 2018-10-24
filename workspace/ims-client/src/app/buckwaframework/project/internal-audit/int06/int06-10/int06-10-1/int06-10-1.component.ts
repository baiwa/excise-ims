import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';
import { formatter, TextDateTH } from 'helpers/datepicker';
import { AjaxService } from 'services/ajax.service';
import { MessageBarService } from 'services/message-bar.service';
import { IaFollowUpProject } from 'models/IaFollowUpProject';
import { ActivatedRoute } from '@angular/router';


declare var $: any;
@Component({
  selector: 'app-int06-10-1',
  templateUrl: './int06-10-1.component.html',
  styleUrls: ['./int06-10-1.component.css']
})
export class Int06101Component implements OnInit {
  num1: number[];
  num2: number[];
  iaFollowUpProject: IaFollowUpProject;
  $form: any;
  id: any;
  percent1: string[];
  percent2: string[];
  percent3: string[];
  breadcrumb: BreadCrumb[]
  pmmethodList : any;
  activityList : any;
  budgetList : any;
  budged : any;
  listButton: any;
  numberButton: number;
  numbers: number[];
  category : any;
  list : any;
  travelToDescription: any;


 
  constructor(
    private messageBarService: MessageBarService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private ajaxService: AjaxService,
    private ajax: AjaxService,) {
      this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ทะเบียนคุมการรับจ่ายเงิน", route: "#" },
      { label: "บันทึกรายการขอเบิก", route: "#" },
      
    ];
    this.listButton = [];
    this.numberButton = 1;
     }

  ngOnInit() {
    $('.ui.calendar').calendar();



     $('.ui.calendar').calendar({    
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
     formatter: formatter()
    });
    
    $(".ui.dropdown.ai").dropdown().css('width', '100%');
    this.listButton.push(this.numberButton);
    this.authService.reRenderVersionProgram('INT-06101');
    this.budgeDropdown();
    this.calenda();
    
    this.pmmethod();
    this.activity();
    this.budge();
    this.numbers = [];
    this.numbers.push(this.numbers.length+1);
  }


  
  onAddField = () => {
    let num = this.numbers.length;
    if (num < 30) {
      this.numbers.push(num + 1);
    } else {
      this.messageBarService.errorModal(
        "ไม่สามารถทำรายการได้",
        "เกิดข้อผิดพลาด"
      );
    }
  };

  calenda = () => {


    $("#dateWithdraw").calendar({    
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
     formatter: formatter()
    });
  }

  saveData() {
    if (!$('#refnum').val()) {
      this.messageBarService.alert("กรุณากรอกเลขที่เอกสารอ้างอิง", "แจ้งเดือน");
      return;
    }

    if (!$('#withdrawal').val()) {
      this.messageBarService.alert("กรุณากรอกวันที่ขอเบิก", "แจ้งเดือน");
      return;
    }

    if (!$('#activity').val()) {
      this.messageBarService.alert("กรุณากรอกกิจกรรม", "แจ้งเดือน");
      return;
    }
    if (!$('#budged').val()) {
      this.messageBarService.alert("กรุณากรอกงบ", "แจ้งเดือน");
      return;
    }
    if (!$('#category').val()) {
      this.messageBarService.alert("กรุณากรอกหมวดย่อย", "แจ้งเดือน");
      return;
    }
    if (!$('#budget').val()) {
      this.messageBarService.alert("กรุณากรอกประเภทงบประมาณ", "แจ้งเดือน");
      return;
    }
    if (!$('#amountOfMoney').val()) {
      this.messageBarService.alert("กรุณากรอกจำนวนเงินขอเบิก", "แจ้งเดือน");
      return;
    }
    if (!$('#pmmethod').val()) {
      this.messageBarService.alert("กรุณากรอกวิธีการจ่าย", "แจ้งเดือน");
      return;
    }
    if (!$('#datePersons').val()) {
      this.messageBarService.alert("กรุณากรอกวันที่สั่งจ่าย", "แจ้งเดือน");
      return;
    }
    if (!$('#amountPaid').val()) {
      this.messageBarService.alert("กรุณากรอกจำนวนเงินที่จ่าย", "แจ้งเดือน");
      return;
    }
    if (!$('#payee').val()) {
      this.messageBarService.alert("กรุณากรอกผู้รับเงิน", "แจ้งเดือน");
      return;
    } 

      const URL = "ia/int06101/add";
      this.ajax.post(URL, {        
        refnum :$("#refnum").val(),
        withdrawal :$("#withdrawal").val(),
        activity:$("#activity").val(),
        budged:$("#budged").val(),
        category:$("#category").val(),
        list:$("#list").val(),
        budget:$("#budget").val(),
        amountOfMoney:$("#amountOfMoney").val(),
        deductSocial:$("#deductSocial").val(),
        withholding:$("#withholding").val(),
        other:$("#other").val(),
        amountOfMoney1:$("#amountOfMoney1").val(),
        numberRequested:$("#numberRequested").val(),
        documentNumber:$("#documentNumber").val(),
        itemDescription:$("#itemDescription").val(),
        note:$("#note").val(),
    
      }, res => {
        const msg = res.json();
        
      if (msg.messageType == "C") {
        this.messageBarService.successModal(msg.messageTh);
      } else {
        this.messageBarService.errorModal(msg.messageTh);
      }
      $("#searchFlag").val("TRUE");
      $('#tableData').DataTable().ajax.reload();
      });
    }
  


  onAddButton = () => {
    console.log("Add Button");
    this.listButton.push(++this.numberButton);
    console.log(this.listButton);

    $(".frame").calendar({    
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
     formatter: formatter()
    });
    
  }

  deleteButton = (e) => {
    console.log("Delete Button : ", e);
    let id = "#" + e;
    let idButton = "#delete" + e;
    $(id).remove();
    let index = this.listButton.findIndex(obj => obj == e);
    this.listButton.splice(index, 1);
  }



  pmmethod = () => {
    let url = "ia/int06101/pmmethod"
    this.ajax.post(url,{}, res => {
      this.pmmethodList = res.json();
    })    
  }

  activity = () => {
    let url = "ia/int06101/activity"
    this.ajax.post(url,{}, res => {
      this.activityList = res.json();
    })    
  }

  budge = () => {
    let url = "ia/int06101/budge"
    this.ajax.post(url,{}, res => {
      this.budgetList = res.json();
    })  
  }

  budgeDropdown = () => {
    const URL = "ia/int06101/budged";
    this.ajax.post(URL,{}, res => {
      this.budged = res.json();
      console.log(this.budged);
    });
  }

  budgedOnchange = (e) => {
    $("#category").dropdown('restore defaults');
    const URL = "ia/int06101/category";  
    let params = e.target.value;
    if (params != "") {
      this.ajax.post(URL,{budgetId : params}, res => {
        console.log("Id : ", res.json());
        this.category = res.json();
      });
    }
  }

  categoryOnchange = (e) => {
    $("#list").dropdown('restore defaults');
    const URL = "ia/int06101/list";
    let params = e.target.value;
    if (params != "") {
      this.ajax.post(URL, {categoryId :params }, res => {
        console.log("Id : ", res.json());
        this.list = res.json();
      });
    }
  }



}
