import { Component, OnInit } from '@angular/core';
import { Int0611Service } from './int06-1-1.service';
import { BreadCrumb } from '../../../../../common/models';
import { Int061Service } from 'projects/internal-audit/int06/int06-1/int06-1.service';

declare var $: any;

@Component({
  selector: 'app-int06-1-1',
  templateUrl: './int06-1-1.component.html',
  styleUrls: ['./int06-1-1.component.css'],
  providers: [Int0611Service]
})
export class Int0611Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบเบิกจ่าย", route: "#" },
    { label: "ตรวจสอบค่าใช้จ่าย", route: "#" },
  ];
  loading: boolean;
  tableLoading : boolean;
  show : boolean = true;
  
  constructor(
    private int0611Service : Int0611Service,
    private int061Service : Int061Service
  ) {
    this.loading = false;
    this.tableLoading = false;   
  }


  ngOnInit() {
  } 
  ngAfterViewInit() {
    this.dataTable();
  }

  async onSubmit(f: any) { 
    
    this.tableLoading = await true; 
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    this.int0611Service.onSubmit(formBody,this.int061Service).then(()=>{
      this.tableLoading = false; 
      this.show = false;  
    });
  }

  async onChangeUpload(file:any){  
    this.loading = await true;
    await this.int0611Service.onChangeUpload(file);
    setTimeout(() => {
      this.loading = false;
    }, 300);
  }

  dataTable(){
    this.int0611Service.dataTable();
  }


  claer=()=>{
    this.show = true;
    this.int0611Service.claer(this.int061Service);
  }
}


