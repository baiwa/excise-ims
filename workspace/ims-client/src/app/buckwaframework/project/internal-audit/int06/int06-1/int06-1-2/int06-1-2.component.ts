import { Component, OnInit } from '@angular/core';
import { Int0612Service } from 'projects/internal-audit/int06/int06-1/int06-1-2/int06-1-2.service';
import { BreadCrumb } from 'models/breadcrumb';
import { Int061Service } from 'projects/internal-audit/int06/int06-1/int06-1.service';
declare var $: any;
@Component({
  selector: 'app-int06-1-2',
  templateUrl: './int06-1-2.component.html',
  styleUrls: ['./int06-1-2.component.css'],
  providers: [Int0612Service,Int061Service]
})
export class Int0612Component implements OnInit {

  //value init
  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบเบิกจ่าย", route: "#" },
    { label: "ตรวจสอบค่าใช้จ่าย", route: "#" },
  ];
  show: boolean = true;
  loading: boolean = false;
  tableLoading: boolean = false;
  constructor(
    private int0612Service: Int0612Service
  ) { }

  ngOnInit() {
    
  }
  ngAfterViewInit() {
    this.dataTable();
  }

  async onChangeUpload(file: any) {
    this.loading = await true;
    await this.int0612Service.onChangeUpload(file);
    setTimeout(() => {
      this.loading = false;
    }, 300);
  }
  //upload
  async onSubmit(f: any) {

    this.tableLoading = await true;
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    this.int0612Service.onSubmit(formBody).then(() => {
      this.tableLoading = false;
      this.show = false;
    });
  }

  dataTable() {
    this.int0612Service.dataTable();
  }

  claer = () => {
    this.show = true;
    this.int0612Service.claer();
  }
}
