import { Component } from "@angular/core";

declare var $:any;

@Component({
    selector: 'ui-buttons',
    templateUrl: './buttons.html',
    styleUrls: ['./buttons.css']
})
export class Buttons {

    color: boolean = false;
    state: boolean = false;
    size: boolean = false;
    align: boolean = false;

    constructor() {
        // TODO
    }

    async copyButton(e: any, what: string) {
        this[what] = await true;
        let classes = await e.target.innerHTML;
        let $temp = await $("<input id='copyToClipboard'>");
        await $("body").append($temp);
        await $temp.val(`<button class="${classes}" type="button">${classes}</button>`).select();
        await document.execCommand("copy");
        await $temp.remove();
        setTimeout(() => { this[what] = false }, 800);
    }

    async copyAligned(e :any) {
        this.align = await true;
        let classes = await e.target.innerHTML;
        let $temp = await $("<textarea id='copyToClipboard'></textarea>");
        await $("body").append($temp);
        await $temp.val(`<div class="ui grid">\r\n\t<div class="sixteen wide ${classes} aligned column">\r\n\t\t<button class="ui button" type="button">${classes}</button>\r\n\t</div>\r\n</div>`).select();
        await document.execCommand("copy");
        await $temp.remove();
        setTimeout(() => { this.align = false }, 800);
    }
}