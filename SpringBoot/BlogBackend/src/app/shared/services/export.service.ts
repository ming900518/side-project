import { Injectable } from "@angular/core";
import jsPDF from 'jspdf'
import autoTable from 'jspdf-autotable'
import Docxtemplater from "docxtemplater";
import PizZip from "pizzip";
import PizZipUtils from "pizzip/utils/index.js";

@Injectable({
    providedIn: "root"
})
export class ExportService {

    constructor(
    ) { }


    /*
    * @param heards Array 
    * @param body Array
    */
    exportExcel(heards: any[], body: any[], fileName: string) {
        import("xlsx").then(xlsx => {
            // heards + body
            let ws_data = heards.concat(body);
            const worksheet = xlsx.utils.aoa_to_sheet(ws_data);
            const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
            const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });
            this.saveAsExcelFile(excelBuffer, fileName);
        });
    }

    saveAsExcelFile(buffer: any, fileName: string): void {
        import("file-saver").then(FileSaver => {
            let EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
            let EXCEL_EXTENSION = '.xlsx';
            const data: Blob = new Blob([buffer], {
                type: EXCEL_TYPE
            });
            FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
        });
    }


    /*
    * @param heards 固定格式 { header:"", dataKey: "d.field" } 
    * @param body  object array {"key":"value"}
    */
    exportPdf(heards: any[], body: any[], fileName: string) {
        const doc = new jsPDF();
        doc.addFont('assets/fonts/SourceHanSansCN-Normal.ttf', 'SourceHanSans-Normal', 'normal');
        doc.setFont('SourceHanSans-Normal');

        autoTable(doc, {
            body: body,
            columns: heards,
            styles: {
                font: "SourceHanSans-Normal",
                //這裏設置字體樣式
                fontStyle: "normal"
            }
        })
        doc.save(fileName + '_export_' + new Date().getTime() + ".pdf")
    }

    getBase64Image(img) {
        var canvas = document.createElement("canvas");
        canvas.width = img.width;
        canvas.height = img.height;
        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0);
        var dataURL = canvas.toDataURL("image/png");
        return dataURL;
    }

    qrCodePdf(qrcode, fileName: string) {
        let doc = new jsPDF();
        let imageData = this.getBase64Image(qrcode.firstChild.firstChild);
        doc.addImage(imageData, 10, 10, 100, 100);
        doc.save(fileName + '.pdf');
    }


    loadFile(url, callback) {
        PizZipUtils.getBinaryContent(url, callback);
    }


    readWord(inputFileName: string, ouptutFileName: string, data: any) {
        this.loadFile(inputFileName, function (
            error,
            content
        ) {
            if (error) {
                throw error;
            }
            var zip = new PizZip(content);
            var doc = new Docxtemplater().loadZip(zip);
            doc.setData(data);
            try {
                // render the document (replace all occurences of {first_name} by John, {last_name} by Doe, ...)
                doc.render();
            } catch (error) {
                // The error thrown here contains additional information when logged with JSON.stringify (it contains a properties object containing all suberrors).
            }
            var out = doc.getZip().generate({
                type: "blob",
                mimeType:
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            }); //Output the document using Data-URI

            import("file-saver").then(FileSaver => {
                FileSaver.saveAs(out, ouptutFileName);
            })

        });
    }
}