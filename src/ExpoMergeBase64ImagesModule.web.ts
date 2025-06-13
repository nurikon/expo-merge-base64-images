import { registerWebModule, NativeModule } from 'expo';

import { ExpoMergeBase64ImagesModuleEvents } from './ExpoMergeBase64Images.types';

class ExpoMergeBase64ImagesModule extends NativeModule<ExpoMergeBase64ImagesModuleEvents> {
  PI = Math.PI;
  async setValueAsync(value: string): Promise<void> {
    this.emit('onChange', { value });
  }
  hello() {
    return 'Hello world! 👋';
  }
}

export default registerWebModule(ExpoMergeBase64ImagesModule, 'ExpoMergeBase64ImagesModule');
