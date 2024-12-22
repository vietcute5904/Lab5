package hust.soict.dsai.aims;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import hust.soict.dsai.aims.cart.*;
import hust.soict.dsai.aims.exception.PlayerExceptionVhq;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.StoreVietHQ;

public class AimsVietHQ {
    public static Scanner sc = new Scanner(System.in);
    public static StoreVietHQ store = new StoreVietHQ();
    public static CartVietHQ cart = new CartVietHQ();
    public static void main(String[] args) {
        List<MediaVhq> mediaList = new ArrayList<>(); 
        mediaList.add(new CompactDiscVhq(1, "Big Bang concert", "Music", "G-Dragon", "Top", 180, 9f));
        mediaList.add(new DVDVietHQ("Dragon", "Anime", 12f));
        mediaList.add(new BookVhq(3, "Book 10000 question why", "hihi", 5f));
        for (MediaVhq media : mediaList) {
            store.addMediaVhq(media); // Thêm từng media vào store
        }
        showMenu();
    }

    public static void showMenu() {
        while(true) {
            System.out.println("AIMS of Hoang Quoc Viet 20225684: ");
            System.out.println("--------------------");
            System.out.println("1. View store");
            System.out.println("2. Update store");
            System.out.println("3. See current cart");
            System.out.println("0. Exit");
            System.out.println("--------------------");
            System.out.println("Please choose a number: 0-1-2-3");
            System.out.print("Your option is: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    storeMenu();
                    continue;
                case 2:
                    updateStoreMenu();
                    continue;
                case 3: 
                    cart.printCartVhq();
                    cartMenu();
                    continue;
                default:
                    System.out.println("Invalid choice. Please choose again!");
                    continue;
            }
            break;
        }
    }
    public static void storeMenu() {
        while(true) {
            System.out.println("View Store Options: ");
            System.out.println("--------------------");
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------");
            System.out.println("Please choose a number: 0-1-2-3-4");
            System.out.print("Your option is: ");

            String title;
            MediaVhq item;
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    System.out.println("Enter the media title: ");
                    sc.nextLine();
                    title = sc.nextLine();
                    item = store.searchByTitleVhq(title);
                    if(item != null) {
                        store.printMediaInfo(item);
                        mediaDetailsMenu(item);
                    }
                    continue;
                case 2:
                    System.out.println("List of medias in store");
                    store.displayStoreVhq();
                    System.out.print("Enter the title of media you want to add to cart: ");
                    sc.nextLine();
                    title = sc.nextLine();
                    item = store.searchByTitleVhq(title);
                    if (item != null) {
                        cart.addMediaVhq(item);
                    }
                    continue;
                case 3:
                    System.out.println("List of medias in store");
                    store.displayStoreVhq(); 
                    System.out.print("Enter the title of media you want to play: ");
                    sc.nextLine(); 
                    title = sc.nextLine();
                    item = store.searchByTitleVhq(title); 
                    if (item != null) {
                        if (item instanceof PlayableVhq) {
                            if (item instanceof CompactDiscVhq || item instanceof DVDVietHQ) {
                                // Nếu là CD hoặc DVD, thì cho phép phát
                               try{
                                   ((PlayableVhq) item).play();
                               }catch (PlayerExceptionVhq e) {
                                   System.err.println(e.getMessage());
                               }
                            } else {
                                System.out.println("This item is not playable");
                            }
                        } else {
                            // Nếu media không phải là PlayableVhq
                            System.out.println("This item is not playable");
                        }
                    } else {
                        System.out.println("No media with the title \"" + title + "\" found.");
                    }
                    continue;
                case 4:
                    cart.printCartVhq();
                    continue;   
                default:
                    System.out.println("Invalid choice. Please choose again!");
                    continue;
            }
            break;
        }
    }

    public static void mediaDetailsMenu(MediaVhq item){
        while (true) {
            System.out.println("Media Details Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            System.out.println("2. Play");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0, 1, 2");
            System.out.print("Your option is: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    cart.addMediaVhq(item);
                    continue;
                case 2:
                    if (item instanceof BookVhq) {
                        System.out.println("Books are not playable");
                    } else if (item instanceof PlayableVhq) {
                        try{
                            ((PlayableVhq) item).play();
                        }catch (PlayerExceptionVhq e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    continue;
                default:
                    System.out.println("Invalid choice. Please choose again!");
                    continue;
            }
            break;
        }
    }

    public static void updateStoreMenu() {
        while(true) {
            System.out.println("Update Store Options");
            System.out.println("--------------------------------");
            System.out.println("1. Add a media");
            System.out.println("2. Remove a media");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Your option is: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    MediaVhq newItem = createNewMedia();
                    store.addMediaVhq(newItem);
                    continue;
                case 2:
                    System.out.print("Enter the title of media to remove:");
                    sc.nextLine();
                    String title = sc.nextLine();
                    MediaVhq media = store.searchByTitleVhq(title);
                    store.removeMediaVhq(media);
                    continue;
                default:
                    System.out.println("Invalid choice. Please choose again!");
                    continue;
            }
            break;
        }
    }
    public static MediaVhq createNewMedia() {
        MediaVhq item;
        System.out.println("Enter the info");
        System.out.print("Title: ");
        sc.nextLine();
        String title = sc.nextLine();
        System.out.print("Cost: ");
        float cost = sc.nextFloat();
        while (true) {
            System.out.println("Choose a media type:");
            System.out.println("1. Book");
            System.out.println("2. DVD");
            System.out.println("3. CD");
            System.out.println("0. Back");
            System.out.print("--> ");
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    return null;
                case 1:
                    item = new BookVhq(title, cost);
                    break;
                case 2:
                    item = new DVDVietHQ(title, cost);
                    break;
                case 3:
                    item = new CompactDiscVhq(title, cost);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again!");
                    continue;
            }
            break;
        }
        return item;
    }

    public static void cartMenu() {
        while(true) {
            System.out.println("Cart Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter medias in cart");
            System.out.println("2. Sort medias in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4-5");
            System.out.print("Your option is: ");
            
            int choice = sc.nextInt();
            String title;
            int id;
            MediaVhq item;
            switch (choice) {
                case 0:
                    break;
                case 1:
                    System.out.println("You want to filter by: \n1. Id \n2. Title");
                    int pick = sc.nextInt();
                    switch (pick) {
                        case 1:
                            System.out.println("Enter a id");
                            id = sc.nextInt();
                            cart.searchByIDVhq(id);
                            break;
                        case 2:
                            System.out.print("Enter a title: ");
                            sc.nextLine();
                            title = sc.nextLine();
                            cart.searchByTitleVhq(title);
                            break;
                        default:
                            break;
                    }
                    continue;
                case 2:
                    System.out.println("Choose the sorting option:");
                    System.out.println("1. Sort by title \n2. Sort by cost");
                    System.out.print("Your choice is: ");
                    int Pick = sc.nextInt();
                    switch (Pick) {
                        case 1:
                            cart.sortCartByTitleThenCost();
                            cart.printCartVhq();
                            break;
                        case 2:
                            cart.sortCartByCostThenTitle();
                            cart.printCartVhq();
                            break;
                        default:
                            break;
                    }
                    continue;
                case 3:
                    System.out.println("Remove media from cart by:");
                    System.out.println("1. By id \n2. By title");
                    int option = sc.nextInt();
                    switch (option) {
                        case 1:
                            System.out.println("Enter a id");
                            id = sc.nextInt();
                            item = cart.searchByIDVhq(id);
                            cart.removeMediaVhq(item);
                            break;
                        case 2:
                            System.out.print("Enter a title: ");
                            sc.nextLine();
                            title = sc.nextLine();
                            item = cart.searchByTitleVhq(title);
                            cart.removeMediaVhq(item);
                            break;
                        default:
                            break;
                    }
                    continue;
                case 4:
                    System.out.print(("Enter a title: "));
                    sc.nextLine();
                    title = sc.nextLine();
                    item = cart.searchByTitleVhq(title);
                    if (item == null) {
                        continue;
                    }
                    if (item instanceof PlayableVhq) {
                        try{
                            ((PlayableVhq) item).play();
                        }catch (PlayerExceptionVhq e) {
                            System.err.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Item not playable");
                    }
                    continue;
                case 5:
                    if (cart.isEmptyVhq()) { 
                        System.out.println("Your cart is empty." +
                        " Please add items to your cart before placing an order.");
                    } else {
                        System.out.println("Order info:");
                        System.out.println("Total cost: " + cart.totalCostVhq());
                        System.out.println("1. Continue\t2. Back");
                        System.out.print("Your choice is:");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                cart.clearVhq();
                                System.out.println("Order placed successfully");
                                break; // Thoát khỏi switch
                            case 2:
                                break; // Thoát khỏi switch
                            default:
                                System.out.println("Invalid choice.");
                                break;
                        }
                    }
                    continue;
                default:
                    System.out.println("Invalid choice. Please choose again!");
                    break;
            }
            break;
        }
    }
}
