package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Table {
    private WebElement webTable;

    public Table(WebElement webTable)
    {
        this.webTable = webTable;
    }

    public WebElement getWebTable() {
        return webTable;
    }

    public int getRowCount() {
        List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
        return tableRows.size();
    }

    public int getColumnCount() {
        WebElement tableRow = webTable.findElement(By.tagName("tr"));
        //WebElement headerRow = tableRows.get(0);
        List<WebElement> tableCols = tableRow.findElements(By.tagName("td"));
        return tableCols.size();
    }

    public WebElement getCell(int rowIdx, int colIdx) {
        try {
            List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
            WebElement currentRow = tableRows.get(rowIdx-1);
            List<WebElement> tableCols = currentRow.findElements(By.tagName("td"));
            WebElement cell = tableCols.get(colIdx-1);
            return cell;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Failed to get cell editor");
        }
    }

    public WebElement getCell(int rowIdx, String colName) {
        try {
            List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
            WebElement currentRow = tableRows.get(rowIdx-1);
            switch (colName) {
                case "First name": return currentRow.findElement(By.className("first-name"));
                case "Last name" : return currentRow.findElement(By.className("last-name"));
                case "Email" : return currentRow.findElement(By.className("email"));
                case "Dues" : return currentRow.findElement(By.className("dues"));
                case "Website" : return currentRow.findElement(By.className("web-site"));
                case "Action" : return currentRow.findElement(By.className("action"));
                default: return null;
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Failed to get cell editor");
        }
    }

    public WebElement getCellEditor(int rowIdx, int colIdx, int editorIdx) {
        try {
            WebElement cellEditor = getCell(rowIdx, colIdx).findElements(By.tagName("input")).get(editorIdx - 1);
            return cellEditor;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Failed to get cell editor");
        }
    }

    public WebElement getCellLink(int rowIdx, int colIdx, int linkIdx) {
        try {
            WebElement cellEditor = getCell(rowIdx, colIdx).findElements(By.tagName("a")).get(linkIdx - 1);
            return cellEditor;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Failed to get cell editor");
        }
    }

    public WebElement getCellLink(int rowIdx, int colIdx) {
        return getCellLink(rowIdx, colIdx, 1);
    }


}
