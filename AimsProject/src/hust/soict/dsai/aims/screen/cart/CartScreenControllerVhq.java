package hust.soict.dsai.aims.screen.cart;
import hust.soict.dsai.aims.cart.CartVhq;
import hust.soict.dsai.aims.media.CompactDiscVhq;
import hust.soict.dsai.aims.media.DVDVhq;
import hust.soict.dsai.aims.media.MediaVhq;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CartScreenControllerVhq {
    private CartVhq cart;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnRemove;
    @FXML
    private TableColumn<MediaVhq, String> colMediaCategory;
    @FXML
    private TableColumn<MediaVhq, Float> colMediaCost;
    @FXML
    private TableColumn<MediaVhq, String> colMediaTitle;
    @FXML
    private ToggleGroup filterCategory;
    @FXML
    private TableView<MediaVhq> tblMedia;
    @FXML
    private RadioButton radioBtnFilterId;
    @FXML
    private RadioButton radioBtnFilterTitle;
    @FXML
    private TextField tfFilter;
    @FXML
    private Label lblTotalCost;
    @FXML
    private Button btnPlaceOrder;

    public CartScreenControllerVhq(CartVhq cart) {
        super();
        this.cart = cart;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitleVhq()));
        colMediaCategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoryVhq()));
        colMediaCost.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCostVhq()));
        tblMedia.setItems(FXCollections.observableArrayList(cart.getItemsVhq()));

        btnPlaceOrder.setOnMouseEntered(event -> btnPlaceOrder.setCursor(javafx.scene.Cursor.HAND));
        btnPlaceOrder.setOnMouseExited(event -> btnPlaceOrder.setCursor(javafx.scene.Cursor.DEFAULT));

        btnPlay.setVisible(true);
        btnRemove.setVisible(true);
        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<MediaVhq>() {
                    @Override
                    public void changed(ObservableValue<? extends MediaVhq> observable, MediaVhq oldValue, MediaVhq newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue); // Cập nhật lại trạng thái khi chọn item mới
                        }
                    }
                });
        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilterMedia(newValue);
            }
        });
        updateTotalCost();
    }

    void updateButtonBar(MediaVhq media) {
        btnRemove.setVisible(true);  // Luôn hiển thị nút Remove

        // Kiểm tra kiểu media và xác định có thể phát không
        if (media instanceof DVDVhq || media instanceof CompactDiscVhq) {
            btnPlay.setVisible(true);  // Hiển thị nút Play nếu là DVD hoặc CD
        } else {
            btnPlay.setVisible(false); // Ẩn nút Play nếu không phải DVD hoặc CD (ví dụ là Book)
        }
    }

    void showFilterMedia(String s) {
        cart.searchByTitleVhq(s);
    }

    @FXML
    private void btnRemovePressed(ActionEvent event) {
        MediaVhq media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMediaVhq(media);
            // Refresh the TableView to reflect the updated cart
            tblMedia.setItems(FXCollections.observableArrayList(cart.getItemsVhq()));

            updateTotalCost();
        }
    }

    @FXML
    private void btnPlaceOrderPressed(ActionEvent event) {
        // Hiển thị thông báo đặt hàng thành công
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been placed successfully!");
        alert.showAndWait();

        // Xóa tất cả các mặt hàng khỏi giỏ hàng
        cart.clearVhq();

        // Cập nhật lại tổng giá trị
        updateTotalCost();

        // Cập nhật lại giao diện
        tblMedia.setItems(FXCollections.observableArrayList(cart.getItemsVhq()));
    }

    private void updateTotalCost() {
        float totalCost = 0;
        for (MediaVhq media : cart.getItemsVhq()) {
            totalCost += media.getCostVhq();
        }

        float finalTotalCost = totalCost;
        Platform.runLater(() -> {
            lblTotalCost.setText(String.format("Total: %.2f $", finalTotalCost));
        });
    }

}
