import { Injectable } from '@angular/core';

// models
import { AlertMessage } from './../models/alertMessage';

declare var $: any;

@Injectable()
export class MessageBarService {

    public static INFO_ICON: string = "info icon";
    public static SUCCESS_ICON: string = "checkmark icon";
    public static ERROR_ICON: string = "warning sign icon";
    private messageList: AlertMessage[] = [];

    constructor() {

    }

    info(message: string) {
        let msg: AlertMessage = new AlertMessage();
        msg.header = "Info";
        msg.message = message;
        msg.iconType = MessageBarService.INFO_ICON;
        this.messageList.push(msg);
    }

    success(message: string) {
        let msg: AlertMessage = new AlertMessage();
        msg.header = "Success";
        msg.message = message;
        msg.iconType = MessageBarService.SUCCESS_ICON;
        this.messageList.push(msg);
    }

    error(message: string) {
        let msg: AlertMessage = new AlertMessage();
        msg.header = "Error";
        msg.message = message;
        msg.iconType = MessageBarService.ERROR_ICON;
        this.messageList.push(msg);
    }

    getMessageList(): AlertMessage[] {
        return this.messageList;
    }

    clear() {
        this.messageList.length = 0;
    }

    comfirm(func: Function, message: string, titele: string = "Confirm") {
        $(".baiwa-confirm-modal div.header").html(titele);
        $(".baiwa-confirm-modal div.content").html(message);
        $('.mini.modal').modal({
            onApprove: function (element) {
                console.log("onApprove", element)
                func(true);
            },
            onDeny: function (element) {
                console.log("onDeny", element)
                func(false);
            },
            closable: false,
        }).modal('show');
    }

    alert(message: string, titele: string = "Alert"){
        $(".baiwa-alert div.header").html(titele);
        $(".baiwa-alert div.content").html(message);
        $(".baiwa-alert").modal('show');
    }

    errorModal = (msg: string, title: string = "เกิดข้อผิดพลาด") => {
        $("#alert div.title").html(title);
        $("#alert div.content").html(msg);
        $("#alert").modal('show');
    }

}