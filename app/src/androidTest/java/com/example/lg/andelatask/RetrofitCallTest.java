//package com.example.lg.andelatask;
//
//import com.example.lg.andelatask.Activities.MainActivity;
//import com.example.lg.andelatask.Utilities.REST.ApiClient;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricGradleTestRunner;
//import org.robolectric.annotation.Config;
//import org.robolectric.util.ActivityController;
//
//import static org.hamcrest.Matchers.equalTo;
//
//@Config(constants = BuildConfig.class, sdk = 21,
//    manifest = "app/src/main/AndroidManifest.xml")
//@RunWith(RobolectricGradleTestRunner.class)
//public class RetrofitCallTest {
//
//    private MainActivity mainActivity;
//
//    @Mock
//    private ApiClient mockRetrofitApiImpl;
//
//    @Captor
//    private ArgumentCaptor<Callback<List<YourObject>>> callbackArgumentCaptor;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
//        mainActivity = controller.get();
//
//        // then we need to swap the retrofit api impl. with a mock one
//        // I usually store my retrofit api impl as a static singleton in class RestClient, hence:
//        RestClient.setApi(mockRetrofitApiImpl);
//
//        controller.create();
//    }
//
//    @Test
//    public void shouldFillAdapter() throws Exception {
//        Mockito.verify(mockRetrofitApiImpl)
//            .getYourObject(callbackAgrumentCaptor.capture());
//
//        int objectsQuantity = 10;
//        List<YourObject> list = new ArrayList<YourObject>;
//        for(int i = 0; i < objectsQuantity; ++i) {
//            list.add(new YourObject());
//        }
//
//        callbackArgumentCaptor.getValue().success(list, null);
//
//        YourAdapter yourAdapter = mainActivity.getAdapter(); // obtain adapter
//        // simple test check if adapter has as many items as put into response
//        assertThat(yourAdapter.getItemCount(), equalTo(objectsQuantity));
//    }
//}