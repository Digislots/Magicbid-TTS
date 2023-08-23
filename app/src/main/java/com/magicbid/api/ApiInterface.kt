
 import com.magicbid.util.MagicbidResponse
 import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {


    @GET("/api/getAds/TTS12NOV")
   suspend fun getApptomative(

    ): Response<MagicbidResponse>

}