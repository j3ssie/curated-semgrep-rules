// License: LGPL-3.0 License (c) find-sec-bugs
// scaffold: dependencies=com.hazelcast.hazelcast@3.12.12
package crypto

import com.hazelcast.config.Config
import com.hazelcast.config.MapConfig
import com.hazelcast.config.NetworkConfig
import com.hazelcast.config.SymmetricEncryptionConfig
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.IMap


class HazelcastSymmetricEncryption {
  var cacheMap: IMap[String, String] = null
  def init(): Unit = { //Specific map time to live
    val myMapConfig = new MapConfig()
    myMapConfig.setName("cachetest")
    myMapConfig.setTimeToLiveSeconds(10)
    //Package config
    val myConfig = new Config()

    //Symmetric Encryption
    val symmetricEncryptionConfig = new SymmetricEncryptionConfig
    symmetricEncryptionConfig.setAlgorithm("DESede")
    symmetricEncryptionConfig.setSalt("saltysalt")
    symmetricEncryptionConfig.setPassword("lamepassword")
    symmetricEncryptionConfig.setIterationCount(1337)
    //Weak Network config..
    val networkConfig = new NetworkConfig()
    networkConfig.setSymmetricEncryptionConfig(symmetricEncryptionConfig)
    myConfig.setNetworkConfig(networkConfig)
    Hazelcast.newHazelcastInstance(myConfig)
    cacheMap = Hazelcast.getOrCreateHazelcastInstance.getMap("cachetest")
  }

  def put(key: String, value: String): Unit = {
    cacheMap.put(key, value)
  }

  def get(key: String) = cacheMap.get(key)
}
